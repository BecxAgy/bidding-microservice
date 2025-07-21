package com.becxagy.book.api.core.usecase.bidding;

import com.becxagy.book.api.application.command.bidding.CreateBiddingCommand;

public interface CreateBiddingUsecase {
    void create(CreateBiddingCommand command);
}
