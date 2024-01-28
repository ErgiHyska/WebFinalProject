package com.UniversalRent.UniversalRent.aspect;

import com.UniversalRent.UniversalRent.entity.Contract;
import com.UniversalRent.UniversalRent.entity.Vehicle;
import com.UniversalRent.UniversalRent.service.VehicleService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class VehicleAspect {
    private final VehicleService vservice;
    public VehicleAspect(VehicleService vservice){this.vservice=vservice;}

    @Before("execution(* com.UniversalRent.UniversalRent.controller.VehicleController.deleteVehicle(..)) && args(id,..)")
    public void logBeforeDeleteContract(Long id) {
        Vehicle temp = vservice.get(id);
        List<Contract> contracts =temp.getContracts();
        List<Contract> newList = new ArrayList<>();
        for (Contract contract : contracts) {
            contract.setVehicle(null);
            newList.add(contract);
        }
        temp.setContracts(newList);
        vservice.save(temp);
    }

}
