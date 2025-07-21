package com.becxagy.book.api.core.usecase;

import com.becxagy.book.api.application.command.bidding.PublishBiddingCommand;

public interface PublishToQueueUsecase {
    void publishBidding(PublishBiddingCommand command);
}