package com.becxagy.book.api.core.usecase.bidding;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.becxagy.book.api.application.representation.bidding.BiddingRepresentation;

public interface ReadBiddingUsecase {

    BiddingRepresentation get(Long biddingId);
    Page<BiddingRepresentation> getAll(Pageable pageable);
}
