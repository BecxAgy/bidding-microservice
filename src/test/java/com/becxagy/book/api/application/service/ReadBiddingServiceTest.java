package com.becxagy.book.api.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.becxagy.book.api.core.domain.bidding.Bidding;
import com.becxagy.book.api.core.repository.BiddingRepository;

@ExtendWith(MockitoExtension.class)
class ReadBiddingServiceTest {

    @Mock
    private BiddingRepository biddingRepository;

    @InjectMocks
    private ReadBiddingService readBiddingService;

    private Bidding testBidding;

    @BeforeEach
    void setUp() {
        testBidding = new Bidding();
        testBidding.setId(1L);
        testBidding.setName("Test Bidding");
        testBidding.setDescription("Test Description");
        testBidding.setFileUrl("https://s3.amazonaws.com/bucket/test.pdf");
    }

    @Test
    void shouldGetBiddingById() {
        // Given
        Long biddingId = 1L;
        when(biddingRepository.get(biddingId)).thenReturn(testBidding);

        // When
        Bidding result = readBiddingService.get(biddingId);

        // Then
        assertEquals(testBidding, result);
        assertEquals(1L, result.getId());
        assertEquals("Test Bidding", result.getName());
        verify(biddingRepository).get(biddingId);
    }

    @Test
    void shouldGetAllBiddings() {
        // Given
        Bidding secondBidding = new Bidding();
        secondBidding.setId(2L);
        secondBidding.setName("Second Bidding");
        
        List<Bidding> expectedBiddings = Arrays.asList(testBidding, secondBidding);
        when(biddingRepository.all()).thenReturn(expectedBiddings);

        // When
        List<Bidding> result = readBiddingService.getAll();

        // Then
        assertEquals(2, result.size());
        assertEquals(expectedBiddings, result);
        verify(biddingRepository).all();
    }

    @Test
    void shouldReturnNullWhenBiddingNotFound() {
        // Given
        Long nonExistentId = 999L;
        when(biddingRepository.get(nonExistentId)).thenReturn(null);

        // When
        Bidding result = readBiddingService.get(nonExistentId);

        // Then
        assertNull(result);
        verify(biddingRepository).get(nonExistentId);
    }
}
