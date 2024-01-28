package com.UniversalRent.UniversalRent.controller;

import com.UniversalRent.UniversalRent.entity.Contract;
import com.UniversalRent.UniversalRent.mappers.RestResponseMapper;
import com.UniversalRent.UniversalRent.request.ContractRequest;
import com.UniversalRent.UniversalRent.service.ContractService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import static com.UniversalRent.UniversalRent.constants.Messages.*;
import com.UniversalRent.UniversalRent.entity.Lender;
import com.UniversalRent.UniversalRent.service.LenderService;
import com.UniversalRent.UniversalRent.entity.Renter;
import com.UniversalRent.UniversalRent.service.RenterService;
import com.UniversalRent.UniversalRent.entity.Vehicle;
import com.UniversalRent.UniversalRent.service.VehicleService;
@Slf4j
@RestController
@RequestMapping("/contract")
public class ContractController {
    private final ContractService contractService;
    private final LenderService lenderService;
    private final RenterService renterService;
    private final VehicleService vehicleService;
    private final ModelMapper modelMapper;
    public ContractController(ContractService contractService,LenderService lenderService,RenterService renterService,
                              VehicleService vehicleService,ModelMapper modelMapper){
        this.contractService=contractService;
        this.lenderService=lenderService;
        this.renterService=renterService;
        this.vehicleService=vehicleService;
        this.modelMapper=modelMapper;
    }
    @GetMapping("/getAll")
    public ResponseEntity<Object> listLenders(){
        List<Contract> contracts = contractService.list();
        return RestResponseMapper.map(SUCCESS,HttpStatus.OK,contracts,RECORDS_RECEIVED);
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){
        Contract contract = contractService.get(id);
        if(contract == null){
            return RestResponseMapper.map(FAIL,HttpStatus.NOT_FOUND,null,NOT_FOUND);
        }
        return RestResponseMapper.map(SUCCESS,HttpStatus.OK,contract,RECORDS_RECEIVED);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> addContract(@RequestBody @Valid ContractRequest contractRequest){
        try{
            Contract contract = modelMapper.map(contractRequest,Contract.class);
            Vehicle vehicle = vehicleService.get(contractRequest.getVehicleId());
            contract.setVehicle(vehicle);
            Renter renter = renterService.get(contractRequest.getRenterId());
            contract.setRenter(renter);
            Lender lender = lenderService.get(contractRequest.getLenderId());
            contract.setLender(lender);
            contract = contractService.save(contract);

            return RestResponseMapper.map(SUCCESS,HttpStatus.OK,contract,RECORD_CREATED);
        }catch (Exception e){
            log.error(e.getMessage());
            return RestResponseMapper.map(FAIL,HttpStatus.INTERNAL_SERVER_ERROR,null,SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Object> deleteContract(@PathVariable Long id){
        Contract contract = contractService.get(id);
        if(contract==null){
            return RestResponseMapper.map(FAIL,HttpStatus.NOT_FOUND,null,NOT_FOUND);
        }
        try{
            contractService.deleteContract(contract);

            return RestResponseMapper.map(SUCCESS,HttpStatus.OK,null,RECORD_DELETED);
        }catch (Exception e){
            log.error(e.getMessage());
            return RestResponseMapper.map(FAIL,HttpStatus.INTERNAL_SERVER_ERROR,null,SERVER_ERROR);
        }
    }
    @PutMapping("/updateById/{id}")
    public ResponseEntity<Object> updateContract(@PathVariable long id,@Valid @RequestBody ContractRequest contractRequest){
        Contract contract = contractService.get(id);
        if(contract==null){
            return RestResponseMapper.map(FAIL,HttpStatus.NOT_FOUND,null,NOT_FOUND);
        }
        try{
            contract = modelMapper.map(contractRequest,Contract.class);
            contract = contractService.update(contract,id);
            return RestResponseMapper.map(SUCCESS,HttpStatus.OK,contract,RECORD_UPDATED);
        } catch (Exception e){
            log.error(e.getMessage());
            return RestResponseMapper.map(FAIL,HttpStatus.INTERNAL_SERVER_ERROR,null,SERVER_ERROR);
        }
    }

}


