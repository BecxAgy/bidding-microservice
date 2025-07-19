package com.becxagy.book.api.core.usecase;

import java.util.List;

import com.becxagy.book.api.core.bidding.Bidding;

public interface ReadBiddingUsecase {

    Bidding get(Long biddingId);
    List<Bidding> getAll();
}
