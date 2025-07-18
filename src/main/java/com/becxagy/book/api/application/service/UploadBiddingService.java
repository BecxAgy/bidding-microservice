package com.becxagy.book.api.application.service;

import com.becxagy.book.api.adapters.out.queue.QueuePort;
import com.becxagy.book.api.adapters.out.storage.StoragePort;
import com.becxagy.book.api.core.usecase.UploadBiddingUsecase;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadBiddingService implements UploadBiddingUsecase {

   
    private final StoragePort storagePort;

    @Autowired
    public UploadBiddingService(QueuePort queuePort, StoragePort storagePort) {
        this.storagePort = storagePort;
       
    }

    @Override
    public String upload(MultipartFile file) {
        CompletableFuture<String> future = storagePort.upload(file);
        return future.join();
    }
}
