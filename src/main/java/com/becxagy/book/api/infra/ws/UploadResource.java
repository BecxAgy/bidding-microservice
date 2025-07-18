package com.becxagy.book.api.infra.ws;


import com.becxagy.book.api.core.command.UploadBiddingCommand;
import com.becxagy.book.api.core.usecase.UploadBiddingUsecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("/v1/bidding")
public class UploadResource {

    private final UploadBiddingUsecase uploadBiddingUsecase;

    @Autowired
    public UploadResource(UploadBiddingUsecase uploadBiddingUsecase){
        this.uploadBiddingUsecase = uploadBiddingUsecase;
    }
    @PostMapping("/upload")
    public ResponseEntity<String> uploadBidding(@ModelAttribute("file") UploadBiddingCommand uploadCommand){
        String fileUrl = uploadBiddingUsecase.upload(uploadCommand.file());

        
        return ResponseEntity.ok(fileUrl);
    }

}
