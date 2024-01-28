package com.UniversalRent.UniversalRent.service;

import com.UniversalRent.UniversalRent.entity.Vehicle;
import com.UniversalRent.UniversalRent.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository repository;

    public VehicleService(VehicleRepository repository) {
        this.repository = repository;
    }

    public List<Vehicle> list() {
        return repository.findAll();
    }

    public Vehicle get(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    public Vehicle save(Vehicle vehicle) {
        return repository.save(vehicle);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    public Vehicle update(Vehicle vehicle, Long id) {
        vehicle.setVehicleId(id);
        return repository.save(vehicle);
    }
}



