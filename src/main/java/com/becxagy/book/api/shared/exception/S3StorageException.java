package com.becxagy.book.api.shared.exception;

public class S3StorageException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    private final String operation;
    private final String bucketName;
    private final String fileName;

    public S3StorageException(String message, String operation, String bucketName, String fileName) {
        super(message);
        this.operation = operation;
        this.bucketName = bucketName;
        this.fileName = fileName;
    }

    public S3StorageException(String message, String operation, String bucketName, String fileName, Throwable cause) {
        super(message, cause);
        this.operation = operation;
        this.bucketName = bucketName;
        this.fileName = fileName;
    }

    public String getOperation() {
        return operation;
    }

    public String getBucketName() {
        return bucketName;
    }

    public String getFileName() {
        return fileName;
    }
}