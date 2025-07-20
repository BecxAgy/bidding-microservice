package com.becxagy.book.api.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import com.becxagy.book.api.adapters.out.queue.QueuePort;
import com.becxagy.book.api.adapters.out.storage.StoragePort;
import com.becxagy.book.api.shared.exception.S3StorageException;

@ExtendWith(MockitoExtension.class)
class UploadBiddingServiceTest {

    @Mock
    private StoragePort storagePort;

    @Mock
    private QueuePort queuePort;

    @InjectMocks
    private UploadBiddingService uploadBiddingService;

    private MockMultipartFile mockFile;

    @BeforeEach
    void setUp() {
        mockFile = new MockMultipartFile(
            "file", 
            "test.pdf", 
            "application/pdf", 
            "test content".getBytes()
        );
    }

    @Test
    void shouldUploadFileSuccessfully() {
        // Given
        String expectedUrl = "https://s3.amazonaws.com/bucket/test.pdf";
        CompletableFuture<String> future = CompletableFuture.completedFuture(expectedUrl);
        
        when(storagePort.upload(mockFile)).thenReturn(future);

        // When
        String result = uploadBiddingService.upload(mockFile);

        // Then
        assertEquals(expectedUrl, result);
        verify(storagePort).upload(mockFile);
    }

    @Test
    void shouldPropagateStorageException() {
        // Given
        S3StorageException storageException = new S3StorageException(
            "Upload failed", "UPLOAD", "test-bucket", "test.pdf"
        );
        CompletableFuture<String> failedFuture = CompletableFuture.failedFuture(storageException);
        
        when(storagePort.upload(mockFile)).thenReturn(failedFuture);

        // When & Then
        CompletionException exception = assertThrows(
            CompletionException.class, 
            () -> uploadBiddingService.upload(mockFile)
        );
        
        assertEquals(storageException, exception.getCause());
        verify(storagePort).upload(mockFile);
    }

    @Test
    void shouldHandleNullFile() {
        // Given
        CompletableFuture<String> future = CompletableFuture.completedFuture("url");
        when(storagePort.upload(null)).thenReturn(future);

        // When
        String result = uploadBiddingService.upload(null);

        // Then
        assertEquals("url", result);
        verify(storagePort).upload(null);
    }
}
