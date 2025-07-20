package com.becxagy.book.api.infra.in.exceptionhandler;

public class S3Error extends StandardError {
    
    private static final long serialVersionUID = 1L;
    
    private String operation;
    private String bucketName;
    private String fileName;

    public S3Error(Integer status, String message, String path, String operation, String bucketName, String fileName) {
        super(status, message, path);
        this.operation = operation;
        this.bucketName = bucketName;
        this.fileName = fileName;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}