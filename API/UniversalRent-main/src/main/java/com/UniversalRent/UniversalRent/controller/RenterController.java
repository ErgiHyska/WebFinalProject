package com.UniversalRent.UniversalRent.controller;

import com.UniversalRent.UniversalRent.entity.Contract;
import com.UniversalRent.UniversalRent.entity.Renter;
import com.UniversalRent.UniversalRent.mappers.RestResponseMapper;
import com.UniversalRent.UniversalRent.request.RenterRequest;
import com.UniversalRent.UniversalRent.service.RenterService;
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
public class RenterController {
    private final RenterService renterService;

    private final ModelMapper modelMapper;

    public RenterController(RenterService renterService, ModelMapper modelMapper) {
        this.renterService = renterService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/renters")
    public ResponseEntity<Object> listRenters() {
        List<Renter> renters = renterService.list();
        return RestResponseMapper.map(SUCCESS, HttpStatus.OK, renters, RECORDS_RECEIVED);
    }

    @GetMapping("/renter/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        Renter renter = renterService.get(id);
        if (renter == null) {
            return RestResponseMapper.map(FAIL, HttpStatus.NOT_FOUND, null, NOT_FOUND);
        }
        return RestResponseMapper.map(SUCCESS, HttpStatus.OK, renter, RECORDS_RECEIVED);
    }

    @GetMapping("/renterAllContracts/{id}")
    public ResponseEntity<Object> listRenterContracts(@PathVariable Long id) {
        Renter renter = renterService.get(id);
        List<Contract> contracts = renter.getContracts();
        return RestResponseMapper.map(SUCCESS, HttpStatus.OK, contracts, RECORDS_RECEIVED);
    }

    @PostMapping("/createRenter")
    public ResponseEntity<Object> addRenter(@RequestBody @Valid RenterRequest renterRequest) {
        try {
            Renter renter = modelMapper.map(renterRequest, Renter.class);
            renter = renterService.save(renter);
            return RestResponseMapper.map(SUCCESS, HttpStatus.OK, renter, RECORD_CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return RestResponseMapper.map(FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null, SERVER_ERROR);
        }

    }

    @DeleteMapping("/deleteRenter/{id}")
    public ResponseEntity<Object> deleteRenter(@PathVariable Long id) {
        Renter renter = renterService.get(id);

        if (renter == null) {
            return RestResponseMapper.map(FAIL, HttpStatus.NOT_FOUND, null, NOT_FOUND);
        }
        try {
            renterService.delete(id);
            return RestResponseMapper.map(SUCCESS, HttpStatus.OK, null, RECORD_DELETED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return RestResponseMapper.map(FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null, SERVER_ERROR);
        }
    }

    @PutMapping("updateRenter/{id}")
    public ResponseEntity<Object> updateRenter(@PathVariable Long id, @Valid @RequestBody RenterRequest renterRequest) {
        Renter renter = renterService.get(id);
        if (renter == null) {
            return RestResponseMapper.map(FAIL, HttpStatus.NOT_FOUND, null, NOT_FOUND);
        }
        try {
            renter = modelMapper.map(renterRequest, Renter.class);
            renter = renterService.update(renter, id);
            return RestResponseMapper.map(SUCCESS, HttpStatus.OK, renter, RECORD_UPDATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return RestResponseMapper.map(FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null, SERVER_ERROR);
        }
    }
}


