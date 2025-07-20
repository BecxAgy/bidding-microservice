package com.becxagy.book.api.core.usecase;

import java.util.List;

import com.becxagy.book.api.application.representation.BiddingRepresentation;

public interface ReadBiddingUsecase {

    BiddingRepresentation get(Long biddingId);
    List<BiddingRepresentation> getAll();
}
