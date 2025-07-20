package com.becxagy.book.api.application.mapper;

import java.util.List;

import com.becxagy.book.api.application.representation.BiddingRepresentation;
import com.becxagy.book.api.application.representation.ChecklistItemRepresentation;
import com.becxagy.book.api.core.domain.bidding.Bidding;

public class BiddingMapper {

    private BiddingMapper() {
        // Prevent instantiation
    }
    
    public static BiddingRepresentation toRepresentation(Bidding bidding) {
        if (bidding == null) {
            return null;
        }
        
        List<ChecklistItemRepresentation> checklistRepresentations = null;
        if (bidding.getChecklist() != null) {
            checklistRepresentations = bidding.getChecklist().stream()
                .map(DocumentRequirementMapper::toRepresentation)
                .toList();
        }
        
        return new BiddingRepresentation(
            bidding.getId(),
            bidding.getName(),
            bidding.getDescription(),
            bidding.getFileUrl(),
            checklistRepresentations
        );
    }
    
    public static List<BiddingRepresentation> toRepresentationList(List<Bidding> biddings) {
        return biddings.stream()
            .map(BiddingMapper::toRepresentation)
            .toList();
    }
}