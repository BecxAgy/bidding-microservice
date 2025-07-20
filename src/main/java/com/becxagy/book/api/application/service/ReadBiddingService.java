package com.becxagy.book.api.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.becxagy.book.api.application.mapper.BiddingMapper;
import com.becxagy.book.api.application.representation.BiddingRepresentation;
import com.becxagy.book.api.core.repository.BiddingRepository;
import com.becxagy.book.api.core.usecase.ReadBiddingUsecase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class ReadBiddingService implements ReadBiddingUsecase{
    private final BiddingRepository biddingRepository;

    @Autowired
    public ReadBiddingService(BiddingRepository biddingRepository) {
        this.biddingRepository = biddingRepository;
    }

    @Override
    public BiddingRepresentation get(Long biddingId) {
      return BiddingMapper.toRepresentation(biddingRepository.get(biddingId));
    }

    @Override
    public Page<BiddingRepresentation> getAll(Pageable pageable) {
        return biddingRepository.all(pageable).map(BiddingMapper::toRepresentation);
    }
}
