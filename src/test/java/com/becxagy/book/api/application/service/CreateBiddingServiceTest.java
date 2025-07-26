package com.becxagy.book.api.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import com.becxagy.book.api.adapters.out.queue.QueuePort;
import com.becxagy.book.api.application.command.bidding.CreateBiddingCommand;
import com.becxagy.book.api.core.repository.bidding.BiddingRepository;
import com.becxagy.book.api.core.usecase.UploadBiddingUsecase;

@ExtendWith(MockitoExtension.class)
class CreateBiddingServiceTest {

    @Mock
    private BiddingRepository biddingRepository;

    @Mock
    private UploadBiddingUsecase uploadBiddingUsecase;

    @Mock
    private QueuePort queuePort;

    @InjectMocks
    private CreateBiddingService createBiddingService;

    private CreateBiddingCommand validCommand;
    private MockMultipartFile mockFile;

    @BeforeEach
    void setUp() {
        mockFile = new MockMultipartFile(
            "file", 
            "test.pdf", 
            "application/pdf", 
            "test content".getBytes()
        );
        
        validCommand = new CreateBiddingCommand(
            mockFile,
            "Test Bidding",
            "Test Description",
            "gemma"
        );
    }

    @Test
    void shouldCreateBiddingSuccessfully() {
        // Given
        String expectedFileUrl = "https://s3.amazonaws.com/bucket/test.pdf";
        Long expectedBiddingId = 1L;
        String expectedModel = "gemma";

        when(uploadBiddingUsecase.upload(mockFile)).thenReturn(expectedFileUrl);
        when(biddingRepository.save("Test Bidding", "Test Description", expectedFileUrl))
            .thenReturn(expectedBiddingId);

        // When
        createBiddingService.create(validCommand);

        // Then
        verify(uploadBiddingUsecase).upload(mockFile);
        verify(biddingRepository).save("Test Bidding", "Test Description", expectedFileUrl);
        verify(queuePort).publish(expectedBiddingId, expectedFileUrl, expectedModel);
    }

    @Test
    void shouldCallServicesInCorrectOrder() {
        // Given
        String fileUrl = "https://s3.amazonaws.com/bucket/test.pdf";
        Long biddingId = 1L;
        String model = "gemma";

        when(uploadBiddingUsecase.upload(any())).thenReturn(fileUrl);
        when(biddingRepository.save(anyString(), anyString(), anyString())).thenReturn(biddingId);

        // When
        createBiddingService.create(validCommand);

        // Then
        var inOrder = inOrder(uploadBiddingUsecase, biddingRepository, queuePort);
        inOrder.verify(uploadBiddingUsecase).upload(mockFile);
        inOrder.verify(biddingRepository).save("Test Bidding", "Test Description", fileUrl);
        inOrder.verify(queuePort).publish(biddingId, fileUrl, model);
    }

    @Test
    void shouldPropagateUploadException() {
        // Given
        RuntimeException uploadException = new RuntimeException("Upload failed");
        when(uploadBiddingUsecase.upload(any())).thenThrow(uploadException);

        // When & Then
        assertThrows(RuntimeException.class, () -> createBiddingService.create(validCommand));
        
        verify(uploadBiddingUsecase).upload(mockFile);
        verify(biddingRepository, never()).save(anyString(), anyString(), anyString());
        verify(queuePort, never()).publish(anyLong(), anyString(), anyString());
    }
}
