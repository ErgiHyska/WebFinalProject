package com.UniversalRent.UniversalRent.service;

import com.UniversalRent.UniversalRent.entity.Contract;
import com.UniversalRent.UniversalRent.repository.ContractRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {

    private final ContractRepository repository;

    public ContractService(ContractRepository repository) {
        this.repository = repository;
    }

    public List<Contract> list() {
        return repository.findAll();
    }

    public Contract get(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    public Contract save(Contract contract) {
        return repository.save(contract);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void deleteContract(Contract contract) {
        repository.delete(contract);
    }

    public Contract update(Contract contract, Long id) {
        contract.setId(id);
        return repository.save(contract);
    }
}



