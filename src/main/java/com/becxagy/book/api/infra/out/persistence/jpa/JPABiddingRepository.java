package com.becxagy.book.api.infra.out.persistence.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.becxagy.book.api.core.domain.bidding.Bidding;
import com.becxagy.book.api.core.repository.bidding.BiddingRepository;
import com.becxagy.book.api.infra.out.persistence.jpa.springdata.SpringDataJPABiddingRepository;
import com.becxagy.book.api.shared.exception.ObjetoNaoEncontradoException;

@Repository
public class JPABiddingRepository implements BiddingRepository {

    private final SpringDataJPABiddingRepository springDataBiddingRepository;

    @Autowired
    public JPABiddingRepository(SpringDataJPABiddingRepository springDataBiddingRepository) {
        this.springDataBiddingRepository = springDataBiddingRepository;
    }

    @Override
    public void add(Bidding obj) {
        springDataBiddingRepository.save(obj);
    }

    @Override
    public void remove(Bidding obj) {
        springDataBiddingRepository.delete(obj);
       
    }

    @Override
    public Bidding get(Long id) {
        return springDataBiddingRepository.findById(id)
                .orElseThrow(ObjetoNaoEncontradoException::new);
       
    }

    @Override
    public boolean exists(Bidding obj) {
        return springDataBiddingRepository.existsById(obj.getId());
    }

    @Override
    public Page<Bidding> all(Pageable pageable) {
            return springDataBiddingRepository.findAll(pageable);
    }

    @Override
    public Long save(String name, String description, String fileUrl) {
        Bidding bidding = new Bidding();
        bidding.setName(name);
        bidding.setDescription(description);
        bidding.setFileUrl(fileUrl);
        
        springDataBiddingRepository.save(bidding);

        return bidding.getId();
    }


}
