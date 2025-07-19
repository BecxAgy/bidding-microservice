package com.becxagy.book.api.infra.persistence.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.becxagy.book.api.core.bidding.Bidding;
import com.becxagy.book.api.core.checklist.DocumentRequirement;
import com.becxagy.book.api.core.repository.BiddingRepository;
import com.becxagy.book.api.infra.persistence.jpa.springdata.SpringDataJPABiddingRepository;

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
                .orElseThrow(() -> new IllegalArgumentException("Bidding with id " + id + " not found"));
       
    }

    @Override
    public boolean exists(Bidding obj) {
        return springDataBiddingRepository.existsById(obj.getId());
    }

    @Override
    public List<Bidding> all() {
        return springDataBiddingRepository.findAll();
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



    @Override
    public void updateChecklist(Long id, List<DocumentRequirement> checklist) {
        Bidding bidding = get(id);
        bidding.setChecklist(checklist);
        springDataBiddingRepository.save(bidding);
    }
    
}
