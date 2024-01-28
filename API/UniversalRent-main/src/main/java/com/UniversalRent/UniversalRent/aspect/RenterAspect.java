package com.UniversalRent.UniversalRent.aspect;

import com.UniversalRent.UniversalRent.entity.Contract;
import com.UniversalRent.UniversalRent.entity.Renter;
import com.UniversalRent.UniversalRent.service.RenterService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class RenterAspect {
    private final RenterService rservice;
    public RenterAspect(RenterService rservice){this.rservice=rservice;}

    @Before("execution(* com.UniversalRent.UniversalRent.controller.RenterController.deleteRenter(..)) && args(id,..)")
    public void logBeforeDeleteContract(Long id) {
        Renter temp = rservice.get(id);
        List<Contract> contracts =temp.getContracts();
        List<Contract> newList = new ArrayList<>();
        for (Contract contract : contracts) {
            contract.setRenter(null);
            newList.add(contract);
        }
        temp.setContracts(newList);
        rservice.save(temp);
    }

}
