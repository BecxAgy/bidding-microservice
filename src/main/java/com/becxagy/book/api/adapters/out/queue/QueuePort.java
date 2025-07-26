package com.becxagy.book.api.adapters.out.queue;


public interface QueuePort {
    void publish(Long biddingId, String fileUrl, String model);
}
