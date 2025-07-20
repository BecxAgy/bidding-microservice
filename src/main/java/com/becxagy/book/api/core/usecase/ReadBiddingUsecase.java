package com.becxagy.book.api.core.usecase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.becxagy.book.api.application.representation.BiddingRepresentation;

public interface ReadBiddingUsecase {

    BiddingRepresentation get(Long biddingId);
    Page<BiddingRepresentation> getAll(Pageable pageable);
}
