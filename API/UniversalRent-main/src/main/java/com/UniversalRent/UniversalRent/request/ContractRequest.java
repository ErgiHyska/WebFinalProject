package com.UniversalRent.UniversalRent.request;


import lombok.Data;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ContractRequest {

    @NotNull(message = "Contract start date is required!")
    private Date startDate;
    @NotNull(message = "Contract end date is required!")
    private Date endDate;
    @NotNull(message = "Contract price is required!")
    private double pricePerDay;
    @NotNull(message = "Renter ID is required!")
    private long renterId;
    @NotNull(message = "Lender ID is required!")
    private long lenderId;
    @NotNull(message = "Vehicle ID is required!")
    private long vehicleId;
}
