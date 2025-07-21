package com.becxagy.book.api.application.service;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.becxagy.book.api.application.command.bidding.UpdateChecklistCommand;
import com.becxagy.book.api.application.mapper.checklist.DocumentRequirementMapper;
import com.becxagy.book.api.application.representation.checklist.ChecklistItemRepresentation;
import com.becxagy.book.api.core.domain.bidding.Bidding;
import com.becxagy.book.api.core.domain.checklist.DocumentRequirement;
import com.becxagy.book.api.core.repository.bidding.BiddingRepository;
import com.becxagy.book.api.core.usecase.bidding.UpdateBiddingUsecase;

@Service
public class UpdateBiddingService implements UpdateBiddingUsecase {

    private final BiddingRepository biddingRepository;

    @Autowired
    public UpdateBiddingService(BiddingRepository biddingRepository) {
        this.biddingRepository = biddingRepository;
    }

    @Override
    public void updateChecklist(Long biddingId, UpdateChecklistCommand command) {

        Bidding bidding = biddingRepository.get(biddingId);

        ArrayList<DocumentRequirement> checklistItems = new ArrayList<>();

        for (ChecklistItemRepresentation document : command.checklistItems())  {
            DocumentRequirement documentRequirement = DocumentRequirementMapper.fromRepresentation(document);
            documentRequirement.setBidding(bidding);
            checklistItems.add(documentRequirement);
        }

        bidding.updateChecklist(checklistItems);
        biddingRepository.add(bidding);
    }
        

}
