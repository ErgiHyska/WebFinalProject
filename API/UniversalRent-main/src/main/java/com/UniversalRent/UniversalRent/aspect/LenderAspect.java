package com.UniversalRent.UniversalRent.aspect;

import com.UniversalRent.UniversalRent.entity.Contract;
import com.UniversalRent.UniversalRent.entity.Lender;
import com.UniversalRent.UniversalRent.service.LenderService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class LenderAspect {
    private final LenderService lservice;
    public LenderAspect(LenderService lservice){this.lservice=lservice;}

    @Before("execution(* com.UniversalRent.UniversalRent.controller.LenderController.deleteLender(..)) && args(id,..)")
    public void logBeforeDeleteContract(Long id) {
        Lender temp = lservice.get(id);
        List<Contract> contracts =temp.getContracts();
        List<Contract> newList = new ArrayList<>();
        for (Contract contract : contracts) {
            contract.setLender(null);
            newList.add(contract);
        }
        temp.setContracts(newList);
        lservice.save(temp);
    }

}
