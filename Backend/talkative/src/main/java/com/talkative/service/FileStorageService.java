package com.talkative.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public interface FileStorageService {

    public String uploadFile(MultipartFile file) throws Exception;

    InputStream getFile(String fileUrl) throws Exception;
}
