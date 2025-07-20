package com.becxagy.book.api.shared.exception;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class S3StorageExceptionTest {

    @Test
    void shouldCreateExceptionWithMessage() {
        // Given
        String message = "Upload failed";
        String operation = "UPLOAD";
        String bucketName = "test-bucket";
        String fileName = "test.pdf";

        // When
        S3StorageException exception = new S3StorageException(message, operation, bucketName, fileName);

        // Then
        assertEquals(message, exception.getMessage());
        assertEquals(operation, exception.getOperation());
        assertEquals(bucketName, exception.getBucketName());
        assertEquals(fileName, exception.getFileName());
        assertNull(exception.getCause());
    }

    @Test
    void shouldCreateExceptionWithCause() {
        // Given
        String message = "Upload failed";
        String operation = "UPLOAD";
        String bucketName = "test-bucket";
        String fileName = "test.pdf";
        RuntimeException cause = new RuntimeException("Root cause");

        // When
        S3StorageException exception = new S3StorageException(message, operation, bucketName, fileName, cause);

        // Then
        assertEquals(message, exception.getMessage());
        assertEquals(operation, exception.getOperation());
        assertEquals(bucketName, exception.getBucketName());
        assertEquals(fileName, exception.getFileName());
        assertEquals(cause, exception.getCause());
    }
}
