package com.talkative.service.impl;

import com.talkative.service.FileStorageService;
import io.minio.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URL;

@Slf4j
@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucketName;

    @Value("${minio.url}")
    private String url;

    @Value("${minio.fetch-url}")
    private String fetchUrl;

    public FileStorageServiceImpl(@Value("${minio.url}") String url,
                              @Value("${minio.access-key}") String accessKey,
                              @Value("${minio.secret-key}") String secretKey) {
        this.minioClient = MinioClient.builder()
                .endpoint(url)
                .credentials(accessKey, secretKey)
                .build();
        try {
            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!bucketExists) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
                log.info("Bucket '{}' created successfully.", bucketName);
            } else {
                log.info("Bucket '{}' already exists.", bucketName);
            }
        } catch (Exception e) {
            log.error("Error checking/creating bucket '{}': {}", bucketName, e.getMessage());
        }
    }

    public String uploadFile(MultipartFile file) throws Exception {

        String objectName = file.getOriginalFilename();

        // Check if the bucket exists, if not, create it
        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }

        // Upload file to MinIO
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build()
        );

        if(fetchUrl != null && !fetchUrl.isEmpty()) {
            objectName = String.format("%s/%s/%s", fetchUrl, bucketName, objectName);
        }
        else {
            objectName = String.format("%s/%s/%s", url, bucketName, objectName);
        }

        return objectName;
    }

    @Override
    public InputStream getFile(String fileUrl) throws Exception{
        // Parse the URL to get the object name (path within the bucket)
        URL url = new URL(fileUrl);
        String objectName = url.getPath().substring(1 + bucketName.length());  // Remove bucket prefix
        // Fetch the file from MinIO
        return minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .build()
        );
    }
}
