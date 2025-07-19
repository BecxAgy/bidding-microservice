package com.becxagy.book.api.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.becxagy.book.api.core.bidding.Bidding;
import com.becxagy.book.api.core.repository.BiddingRepository;
import com.becxagy.book.api.core.usecase.ReadBiddingUsecase;
@Service
public class ReadBiddingService implements ReadBiddingUsecase{
    private final BiddingRepository biddingRepository;
    @Autowired
    public ReadBiddingService(BiddingRepository biddingRepository) {
        this.biddingRepository = biddingRepository;
    }

    @Override
    public Bidding get(Long biddingId) {
       return biddingRepository.get(biddingId);
    }

    @Override
    public List<Bidding> getAll() {
        return biddingRepository.all();
    }
    
    
}
