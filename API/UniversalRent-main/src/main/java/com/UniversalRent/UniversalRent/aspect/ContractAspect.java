package com.UniversalRent.UniversalRent.aspect;

import com.UniversalRent.UniversalRent.entity.Contract;
import com.UniversalRent.UniversalRent.service.ContractService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ContractAspect {
    private final ContractService cservice;
    public ContractAspect(ContractService cservice){this.cservice=cservice;}

    @Before("execution(* com.UniversalRent.UniversalRent.controller.ContractController.deleteContract(..)) && args(id,..)")
    public void logBeforeDeleteContract(Long id) {
        Contract temp = cservice.get(id);
        temp.setVehicle(null);
        temp.setLender(null);
        temp.setRenter(null);
        cservice.save(temp);
    }

}
