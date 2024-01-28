package com.UniversalRent.UniversalRent.service;

import com.UniversalRent.UniversalRent.entity.Lender;
import com.UniversalRent.UniversalRent.repository.LenderRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LenderService {

    private final LenderRepository repository;

    public LenderService(LenderRepository repository) {
        this.repository = repository;
    }
    public List<Lender> list() {
        return repository.findAll();
    }
    public Lender get(Long id) {
        return this.repository.findById(id).orElse(null);
    }
    public Lender save(Lender lender) {
        return repository.save(lender);
    }
    public void delete(Long id) {
        repository.deleteById(id);
    }
    public Lender update(Lender lender, Long id) {
        lender.setLenderId(id);
        return repository.save(lender);
    }
}



