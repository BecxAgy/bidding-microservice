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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.becxagy.book.api.application.representation.bidding.BiddingRepresentation;
import com.becxagy.book.api.core.domain.bidding.Bidding;
import com.becxagy.book.api.core.repository.bidding.BiddingRepository;

@ExtendWith(MockitoExtension.class)
class ReadBiddingServiceTest {

    @Mock
    private BiddingRepository biddingRepository;

    @InjectMocks
    private ReadBiddingService readBiddingService;

    private Bidding testBidding;
    private Bidding secondBidding;

    @BeforeEach
    void setUp() {
        testBidding = new Bidding();
        testBidding.setId(1L);
        testBidding.setName("Test Bidding");
        testBidding.setDescription("Test Description");
        testBidding.setFileUrl("https://s3.amazonaws.com/bucket/test.pdf");
        testBidding.setChecklist(null);

        secondBidding = new Bidding();
        secondBidding.setId(2L);
        secondBidding.setName("Second Bidding");
        secondBidding.setDescription("Second Description");
        secondBidding.setFileUrl("https://s3.amazonaws.com/bucket/second.pdf");
        secondBidding.setChecklist(null);
    }

    @Test
    void shouldGetBiddingById() {
        // Given
        Long biddingId = 1L;
        when(biddingRepository.get(biddingId)).thenReturn(testBidding);

        // When
        BiddingRepresentation result = readBiddingService.get(biddingId);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Bidding", result.getName());
        assertEquals("Test Description", result.getDescription());
        assertEquals("https://s3.amazonaws.com/bucket/test.pdf", result.getFileUrl());
        verify(biddingRepository).get(biddingId);
    }

    @Test
    void shouldGetAllBiddingsWithPagination() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        List<Bidding> biddingList = Arrays.asList(testBidding, secondBidding);
        Page<Bidding> biddingPage = new PageImpl<>(biddingList, pageable, 2);
        
        when(biddingRepository.all(pageable)).thenReturn(biddingPage);

        // When
        Page<BiddingRepresentation> result = readBiddingService.getAll(pageable);

        // Then
        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        assertEquals(2, result.getTotalElements());
        assertEquals(0, result.getNumber());
        assertEquals(10, result.getSize());
        
        // Verify first bidding representation
        BiddingRepresentation firstResult = result.getContent().get(0);
        assertEquals(1L, firstResult.getId());
        assertEquals("Test Bidding", firstResult.getName());
        
        // Verify second bidding representation
        BiddingRepresentation secondResult = result.getContent().get(1);
        assertEquals(2L, secondResult.getId());
        assertEquals("Second Bidding", secondResult.getName());
        
        verify(biddingRepository).all(pageable);
    }

    @Test
    void shouldReturnNullWhenBiddingNotFound() {
        // Given
        Long nonExistentId = 999L;
        when(biddingRepository.get(nonExistentId)).thenReturn(null);

        // When
        BiddingRepresentation result = readBiddingService.get(nonExistentId);

        // Then
        assertNull(result);
        verify(biddingRepository).get(nonExistentId);
    }

    @Test
    void shouldReturnEmptyPageWhenNoBiddingsFound() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        Page<Bidding> emptyPage = new PageImpl<>(Arrays.asList(), pageable, 0);
        when(biddingRepository.all(pageable)).thenReturn(emptyPage);

        // When
        Page<BiddingRepresentation> result = readBiddingService.getAll(pageable);

        // Then
        assertNotNull(result);
        assertTrue(result.getContent().isEmpty());
        assertEquals(0, result.getTotalElements());
        assertEquals(0, result.getNumber());
        verify(biddingRepository).all(pageable);
    }

    @Test
    void shouldHandlePaginationCorrectly() {
        // Given
        Pageable pageable = PageRequest.of(1, 1); // Second page, 1 item per page
        List<Bidding> biddingList = Arrays.asList(secondBidding);
        Page<Bidding> biddingPage = new PageImpl<>(biddingList, pageable, 2);
        
        when(biddingRepository.all(pageable)).thenReturn(biddingPage);

        // When
        Page<BiddingRepresentation> result = readBiddingService.getAll(pageable);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals(2, result.getTotalElements()); // Total elements across all pages
        assertEquals(1, result.getNumber()); // Current page number
        assertEquals(1, result.getSize()); // Page size
        assertEquals(2, result.getTotalPages()); // Total pages
        
        BiddingRepresentation resultBidding = result.getContent().get(0);
        assertEquals(2L, resultBidding.getId());
        assertEquals("Second Bidding", resultBidding.getName());
        
        verify(biddingRepository).all(pageable);
    }
}