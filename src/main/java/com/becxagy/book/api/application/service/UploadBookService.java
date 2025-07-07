package com.becxagy.book.api.application.service;

import com.becxagy.book.api.adapters.out.queue.QueuePort;
import com.becxagy.book.api.core.usecase.UploadBookUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadBookService implements UploadBookUsecase {

    private final QueuePort queuePort;

    @Autowired
    public UploadBookService(QueuePort queuePort){
       this.queuePort = queuePort;
    }

    @Override
    public String upload(MultipartFile file) {
        queuePort.publish(file);
        return "";
    }
}
