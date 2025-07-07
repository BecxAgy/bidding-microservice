package com.becxagy.book.api.adapters.out.queue;

import org.springframework.web.multipart.MultipartFile;

public interface QueuePort {
    void publish(final MultipartFile file);
}
