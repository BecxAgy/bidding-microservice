package com.becxagy.book.api.infra.aws.s3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.becxagy.book.api.adapters.out.storage.StoragePort;
import com.becxagy.book.api.infra.utils.file.FileUtil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Component
public class AmazonS3Storage implements StoragePort {

    private final AmazonS3 s3Client;


    @Value("${aws.s3.bucket}")
    private String bucketName;

    public AmazonS3Storage(AmazonS3 client) {
        this.s3Client = client;
    }

    @Override
    @Async
    public CompletableFuture<String> upload(final MultipartFile multipartFile) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                final File file = FileUtil.convertMultipartFileToFile(multipartFile);
                String fileUrl = uploadFileToS3Bucket(bucketName, file);
                file.delete();
                return fileUrl;
            } catch (final AmazonServiceException ex) {
                throw new RuntimeException("Error while uploading file to S3: " + ex.getMessage());
            }
        });
    }



    private String uploadFileToS3Bucket(final String bucketName, final File file) {
        try {
            final String uniqueFileName = LocalDateTime.now() + "_" + file.getName();
            final PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, uniqueFileName, file);
                    
            s3Client.putObject(putObjectRequest);

            // Retorna a URL do arquivo armazenado
            return s3Client.getUrl(bucketName, uniqueFileName).toString();
        } catch (AmazonServiceException ex) {

            throw new RuntimeException("Failed to upload file to S3: " + ex.getMessage());
        }
    }

    @Override
    public void delete(String filename) {

    }
}
