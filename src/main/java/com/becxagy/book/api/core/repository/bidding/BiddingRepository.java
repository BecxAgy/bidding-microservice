package com.becxagy.book.api.core.repository.bidding;

import java.util.List;

import com.becxagy.book.api.core.domain.bidding.Bidding;
import com.becxagy.book.api.core.repository.Repository;


public interface BiddingRepository extends Repository<Bidding, Long> {
    public Long save(String name,String description, String fileUrl);
    
    

}
