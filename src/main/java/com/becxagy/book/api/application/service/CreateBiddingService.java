package com.becxagy.book.api.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.becxagy.book.api.adapters.out.queue.QueuePort;
import com.becxagy.book.api.application.command.CreateBiddingCommand;
import com.becxagy.book.api.core.repository.BiddingRepository;
import com.becxagy.book.api.core.usecase.CreateBiddingUsecase;
import com.becxagy.book.api.core.usecase.UploadBiddingUsecase;

@Service
public class CreateBiddingService implements CreateBiddingUsecase {

    public final BiddingRepository biddingRepository;
    public final UploadBiddingUsecase uploadBiddingUsecase;
    public final QueuePort queuePort;

    @Autowired
    public CreateBiddingService(BiddingRepository biddingRepository, UploadBiddingUsecase uploadBiddingUsecase, QueuePort queuePort) {
        this.biddingRepository = biddingRepository;
        this.uploadBiddingUsecase = uploadBiddingUsecase;
        this.queuePort = queuePort;
    }

    @Override
    public void create(CreateBiddingCommand command) {
        String fileUrl = uploadBiddingUsecase.upload(command.file());
        
        Long biddingId = biddingRepository.save(command.name(), command.description(), fileUrl);

        queuePort.publish(biddingId, fileUrl);
    }
}
