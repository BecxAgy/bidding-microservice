package com.becxagy.book.api.core.repository;

import java.util.List;

import com.becxagy.book.api.core.domain.bidding.Bidding;


public interface BiddingRepository extends Repository<Bidding, Long> {
    public Long save(String name,String description, String fileUrl);
    
    

}
