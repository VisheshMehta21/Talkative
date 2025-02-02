const { S3Client, PutObjectCommand } = require("@aws-sdk/client-s3");
const fs = require('fs');

const s3Client = new S3Client({
  endpoint: "http://localhost:9000", // MinIO server address
  region: "us-east-1", // MinIO doesn't require a specific region
  credentials: {
    accessKeyId: "minioadmin", // Replace with your access key
    secretAccessKey: "minioadmin" // Replace with your secret key
  },
  forcePathStyle: true // Required for MinIO
});

const formatFileName = (fileName) => {
    return fileName.replace(/\s+/g, '_');
  };
  
  export const uploadImageToMinio = async (file, bucketName = 'talkative') => {
    const fileName = `${Date.now()}-${formatFileName(file.name)}`;
  
    try {
      // const body = file.stream(); 
      const fileContent = fs.readFileSync(file);
  const command = new PutObjectCommand({
    Bucket: bucketName,
    Key: fileName,
    Body: fileContent
  });
  
    const response = await s3Client.send(command);
    console.log("File uploaded successfully", response);
  } catch (err) {
    console.error("Error uploading file", err);
  }
};

async function uploadFile(bucketName, key, filePath) {
  const fileContent = fs.readFileSync(filePath);
  const command = new PutObjectCommand({
    Bucket: bucketName,
    Key: key,
    Body: fileContent
  });

  try {
    const response = await s3Client.send(command);
    console.log("File uploaded successfully", response);
  } catch (err) {
    console.error("Error uploading file", err);
  }
}





/* import { S3Client, PutObjectCommand } from '@aws-sdk/client-s3';

// Polyfill for older browsers (add this at the top of your file)
if (!Blob.prototype.stream) {
  Blob.prototype.stream = function() {
    const blob = this;
    let position = 0;
    return new ReadableStream({
      start(controller) {
        return Promise.resolve();
      },
      pull(controller) {
        if (position >= blob.size) {
          controller.close();
          return;
        }

        const chunk = blob.slice(position, Math.min(position + 16384, blob.size)); // Adjust chunk size if needed
        const reader = new FileReader();

        reader.onloadend = event => {
          const uint8Array = new Uint8Array(event.srcElement.result);
          controller.enqueue(uint8Array);
          position += chunk.size;
          if (position >= blob.size) {
            controller.close();
          }
        };
        reader.readAsArrayBuffer(chunk);
      },
    });
  };
}


const s3Client = new S3Client({
  region: 'us-east-1', // Optional, but good practice
  endpoint: 'https://localhost:9000',
  credentials: {
    accessKeyId: 'minioadmin',
    secretAccessKey: 'minioadmin',
  },
  forcePathStyle: true,
  signatureVersion: 'v4',
});

const formatFileName = (fileName) => {
  return fileName.replace(/\s+/g, '_');
};

export const uploadImageToMinio = async (file, bucketName = 'talkative') => {
  const fileName = `${Date.now()}-${formatFileName(file.name)}`;

  try {
    // const body = file.stream(); 
    const command = new PutObjectCommand({
      Bucket: bucketName,
      Key: fileName,
      Body: file,
      // ContentType: file.type, 
    });

    const response = await s3Client.send(command);
    console.log('Successfully uploaded image to MinIO:', response);

    return `http://localhost:9000/${bucketName}/${fileName}`;

  } catch (error) {
    console.error('Error uploading image to MinIO:', error);
    return null;
  }
};

export const uploadToMinioXHR = async (file, bucketName = 'talkative') => {
    const fileName = `${Date.now()}-${formatFileName(file.name)}`;
    const url = `http://localhost:9000/${bucketName}/${fileName}`;
    const formData = new FormData();
    formData.append('file', file);
  
    try {
      const response = await fetch(url, {
        method: 'PUT',
        headers: {
          'Content-Type': file.type,
          'Authorization': `Bearer ${'minioadmin:minioadmin'}` 
        },
        body: formData,
      });
  
      if (response.ok) {
        console.log('Successfully uploaded file to MinIO ', url);
        return url;
      } else {
        throw new Error('Error uploading file to MinIO');
      }
    } catch (error) {
      console.error('Error uploading file to MinIO:', error);
      return null;
    }
  }; */