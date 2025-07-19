package com.becxagy.book.api.infra.persistence.jpa.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.becxagy.book.api.core.bidding.Bidding;

@Repository
public interface SpringDataJPABiddingRepository extends JpaRepository<Bidding, Long> {

}
