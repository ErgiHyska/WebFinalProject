package com.UniversalRent.UniversalRent.controller;

import com.UniversalRent.UniversalRent.entity.Contract;
import com.UniversalRent.UniversalRent.entity.Vehicle;
import com.UniversalRent.UniversalRent.mappers.RestResponseMapper;
import com.UniversalRent.UniversalRent.request.VehicleRequest;
import com.UniversalRent.UniversalRent.service.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import static com.UniversalRent.UniversalRent.constants.Messages.*;


@Slf4j
@RestController
public class VehicleController {
    private final VehicleService vehicleService;
    private final ModelMapper modelMapper;

    public VehicleController(VehicleService vehicleService, ModelMapper modelMapper) {
        this.vehicleService = vehicleService;
        this.modelMapper = modelMapper;
    }
    @GetMapping("/vehicles")
    public ResponseEntity<Object> listVehicles() {
        List<Vehicle> vehicles = vehicleService.list();
        return RestResponseMapper.map(SUCCESS, HttpStatus.OK, vehicles, RECORDS_RECEIVED);
    }
    @GetMapping("/vehicle/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        Vehicle vehicle = vehicleService.get(id);
        if (vehicle == null) {
            return RestResponseMapper.map(FAIL, HttpStatus.NOT_FOUND, null, NOT_FOUND);
        }
        return RestResponseMapper.map(SUCCESS, HttpStatus.OK, vehicle, RECORDS_RECEIVED);
    }

    @GetMapping("/vehicleAllContracts/{id}")
    public ResponseEntity<Object> listVehicleContracts(@PathVariable Long id) {
        Vehicle vehicle = vehicleService.get(id);
        List<Contract> contracts = vehicle.getContracts();
        return RestResponseMapper.map(SUCCESS, HttpStatus.OK, contracts, RECORDS_RECEIVED);
    }

    @PostMapping("/createVehicle")
    public ResponseEntity<Object> addVehicle(@RequestBody @Valid VehicleRequest vehicleRequest) {
        try {
            Vehicle vehicle = modelMapper.map(vehicleRequest, Vehicle.class);
            vehicle = vehicleService.save(vehicle);
            return RestResponseMapper.map(SUCCESS, HttpStatus.OK, vehicle, RECORD_CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return RestResponseMapper.map(FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null, SERVER_ERROR);
        }

    }

    @DeleteMapping("/deleteVehicle/{id}")
    public ResponseEntity<Object> deleteVehicle(@PathVariable Long id) {
        Vehicle vehicle = vehicleService.get(id);

        if (vehicle == null) {
            return RestResponseMapper.map(FAIL, HttpStatus.NOT_FOUND, null, NOT_FOUND);
        }
        try {
            vehicleService.delete(id);
            return RestResponseMapper.map(SUCCESS, HttpStatus.OK, null, RECORD_DELETED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return RestResponseMapper.map(FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null, SERVER_ERROR);
        }
    }

    @PutMapping("updateVehicle/{id}")
    public ResponseEntity<Object> updateVehicle(@PathVariable Long id, @Valid @RequestBody VehicleRequest vehicleRequest) {
        Vehicle vehicle = vehicleService.get(id);
        if (vehicle == null) {
            return RestResponseMapper.map(FAIL, HttpStatus.NOT_FOUND, null, NOT_FOUND);
        }
        try {
            vehicle = modelMapper.map(vehicleRequest, Vehicle.class);
            vehicle = vehicleService.update(vehicle, id);
            return RestResponseMapper.map(SUCCESS, HttpStatus.OK, vehicle, RECORD_UPDATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return RestResponseMapper.map(FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null, SERVER_ERROR);
        }
    }
}

