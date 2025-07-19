package com.becxagy.book.api.core.usecase;

import com.becxagy.book.api.core.command.PublishBiddingCommand;

public interface PublishToQueueUsecase {
    void publishBidding(PublishBiddingCommand command);
}