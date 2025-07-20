package com.becxagy.book.api.infra.in;


import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.becxagy.book.api.application.command.CreateBiddingCommand;
import com.becxagy.book.api.application.command.UpdateChecklistCommand;
import com.becxagy.book.api.application.representation.BiddingRepresentation;
import com.becxagy.book.api.core.domain.bidding.Bidding;
import com.becxagy.book.api.core.usecase.CreateBiddingUsecase;
import com.becxagy.book.api.core.usecase.ReadBiddingUsecase;
import com.becxagy.book.api.core.usecase.UpdateBiddingUsecase;


@RestController()
@RequestMapping("/v1/bidding")
public class BiddingResource {

    private final CreateBiddingUsecase uploadBiddingUsecase;
    private final ReadBiddingUsecase readBiddingUsecase;
    private final UpdateBiddingUsecase updateBiddingUsecase;

    @Autowired
    public BiddingResource(CreateBiddingUsecase uploadBiddingUsecase, ReadBiddingUsecase readBiddingUsecase, UpdateBiddingUsecase updateBiddingUsecase) {
        this.uploadBiddingUsecase = uploadBiddingUsecase;
        this.readBiddingUsecase = readBiddingUsecase;
        this.updateBiddingUsecase = updateBiddingUsecase;
    }
    @PostMapping("/create")
    public ResponseEntity<String> uploadBidding(@Valid @ModelAttribute("file") CreateBiddingCommand command){
        uploadBiddingUsecase.create(command);
        return ResponseEntity.ok("Bidding created successfully");
    }

    @PatchMapping("/checklist/{biddingId}")
    public ResponseEntity<String> updateBiddingChecklist(@PathVariable Long biddingId, @RequestBody UpdateChecklistCommand command) {
        updateBiddingUsecase.updateChecklist(biddingId, command);
        return ResponseEntity.ok("Bidding checklist updated successfully");
    }

    @GetMapping("/{biddingId}")
    public ResponseEntity<BiddingRepresentation> getBidding(@PathVariable Long biddingId) {
        BiddingRepresentation bidding = readBiddingUsecase.get(biddingId);
        return ResponseEntity.ok(bidding);
    }

    @GetMapping("/")
    public ResponseEntity<List<BiddingRepresentation>> getAllBiddings() {
        List<BiddingRepresentation> biddings = readBiddingUsecase.getAll();
        return ResponseEntity.ok(biddings);
    }

}
