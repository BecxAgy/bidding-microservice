package com.becxagy.book.api.application.service;

import com.becxagy.book.api.adapters.out.queue.QueuePublisherPort;
import com.becxagy.book.api.core.usecase.UploadBookUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadBookService implements UploadBookUsecase {

    private final QueuePublisherPort queuePublisherPort;

    @Autowired
    public UploadBookService(QueuePublisherPort queuePublisherPort){
       this.queuePublisherPort = queuePublisherPort;
    }

    @Override
    public String upload(MultipartFile file) {
        queuePublisherPort.publish(file);
        return "";
    }
}
