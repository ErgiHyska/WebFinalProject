package com.UniversalRent.UniversalRent.controller;

import com.UniversalRent.UniversalRent.entity.Contract;
import com.UniversalRent.UniversalRent.entity.Lender;
import com.UniversalRent.UniversalRent.mappers.RestResponseMapper;
import com.UniversalRent.UniversalRent.request.LenderRequest;
import com.UniversalRent.UniversalRent.service.LenderService;
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
public class LenderController {
    private final LenderService lenderService;
    private final ModelMapper modelMapper;

    public LenderController(LenderService lenderService, ModelMapper modelMapper) {
        this.lenderService = lenderService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/lenders")
    public ResponseEntity<Object> listLenders() {
        List<Lender> lenders = lenderService.list();
        return RestResponseMapper.map(SUCCESS, HttpStatus.OK, lenders, RECORDS_RECEIVED);
    }

    @GetMapping("/lender/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        Lender lender = lenderService.get(id);
        if (lender == null) {
            return RestResponseMapper.map(FAIL, HttpStatus.NOT_FOUND, null, NOT_FOUND);
        }
        return RestResponseMapper.map(SUCCESS, HttpStatus.OK, lender, RECORDS_RECEIVED);
    }

    @GetMapping("/lenderAllContracts/{id}")
    public ResponseEntity<Object> listRenterContracts(@PathVariable Long id) {
        Lender lender = lenderService.get(id);
        List<Contract> contracts = lender.getContracts();
        return RestResponseMapper.map(SUCCESS, HttpStatus.OK, contracts, RECORDS_RECEIVED);
    }

    @PostMapping("/createLender")
    public ResponseEntity<Object> addLender(@RequestBody @Valid LenderRequest lenderRequest) {
        try {
            Lender lender = modelMapper.map(lenderRequest, Lender.class);
            lender = lenderService.save(lender);
            return RestResponseMapper.map(SUCCESS, HttpStatus.OK, lender, RECORD_CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return RestResponseMapper.map(FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null, SERVER_ERROR);
        }

    }

    @DeleteMapping("/deleteLender/{id}")
    public ResponseEntity<Object> deleteLender(@PathVariable Long id) {
        Lender lender = lenderService.get(id);

        if (lender == null) {
            return RestResponseMapper.map(FAIL, HttpStatus.NOT_FOUND, null, NOT_FOUND);
        }
        try {
            lenderService.delete(id);
            return RestResponseMapper.map(SUCCESS, HttpStatus.OK, null, RECORD_DELETED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return RestResponseMapper.map(FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null, SERVER_ERROR);
        }
    }

    @PutMapping("updateLender/{id}")
    public ResponseEntity<Object> updateLender(@PathVariable Long id, @Valid @RequestBody LenderRequest lenderRequest) {
        Lender lender = lenderService.get(id);
        if (lender == null) {
            return RestResponseMapper.map(FAIL, HttpStatus.NOT_FOUND, null, NOT_FOUND);
        }
        try {
            lender = modelMapper.map(lenderRequest, Lender.class);
            lender = lenderService.update(lender, id);
            return RestResponseMapper.map(SUCCESS, HttpStatus.OK, lender, RECORD_UPDATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return RestResponseMapper.map(FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null, SERVER_ERROR);
        }
    }
}


