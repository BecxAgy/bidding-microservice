package com.becxagy.book.api.infra.ws;


import com.becxagy.book.api.core.bidding.Bidding;
import com.becxagy.book.api.core.command.CreateBiddingCommand;
import com.becxagy.book.api.core.usecase.CreateBiddingUsecase;
import com.becxagy.book.api.core.usecase.ReadBiddingUsecase;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("/v1/bidding")
public class BiddingResource {

    private final CreateBiddingUsecase uploadBiddingUsecase;
    private final ReadBiddingUsecase readBiddingUsecase;

    @Autowired
    public BiddingResource(CreateBiddingUsecase uploadBiddingUsecase, ReadBiddingUsecase readBiddingUsecase){
        this.uploadBiddingUsecase = uploadBiddingUsecase;
        this.readBiddingUsecase = readBiddingUsecase;
    }
    @PostMapping("/create")
    public ResponseEntity<String> uploadBidding(@ModelAttribute("file") CreateBiddingCommand command){
        uploadBiddingUsecase.create(command);
        return ResponseEntity.ok("Bidding created successfully");
    }

    @GetMapping("/{biddingId}")
    public ResponseEntity<Bidding> getBidding(@PathVariable Long biddingId) {
        Bidding bidding = readBiddingUsecase.get(biddingId);
        return ResponseEntity.ok(bidding);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Bidding>> getAllBiddings() {
        List<Bidding> biddings = readBiddingUsecase.getAll();
        return ResponseEntity.ok(biddings);
    }

}
