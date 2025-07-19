package com.becxagy.book.api.core.usecase;

import com.becxagy.book.api.core.command.CreateBiddingCommand;

public interface CreateBiddingUsecase {
    void create(CreateBiddingCommand command);
}
