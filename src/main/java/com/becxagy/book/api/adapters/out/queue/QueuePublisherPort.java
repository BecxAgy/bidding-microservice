package com.becxagy.book.api.adapters.out.queue;

import org.springframework.web.multipart.MultipartFile;

public interface QueuePublisherPort {
    void publish(MultipartFile file);
}
