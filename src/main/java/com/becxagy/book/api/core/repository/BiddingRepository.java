package com.becxagy.book.api.core.repository;

import java.util.List;

import com.becxagy.book.api.core.bidding.Bidding;
import com.becxagy.book.api.core.checklist.DocumentRequirement;

public interface BiddingRepository extends Repository<Bidding, Long> {
    public Long save(String name,String description, String fileUrl);
    public void updateChecklist(Long id, List<DocumentRequirement> checklist);
}
