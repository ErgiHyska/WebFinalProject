package com.UniversalRent.UniversalRent.service;

import com.UniversalRent.UniversalRent.entity.Renter;
import com.UniversalRent.UniversalRent.repository.RenterRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RenterService {

    private final RenterRepository repository;

    public RenterService(RenterRepository repository) {
        this.repository = repository;
    }

    public List<Renter> list() {
        return repository.findAll();
    }

    public Renter get(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    public Renter save(Renter renter) {
        return repository.save(renter);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    public Renter update(Renter renter, Long id) {
        renter.setRenterId(id);
        return repository.save(renter);
    }
}



