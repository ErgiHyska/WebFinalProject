package com.UniversalRent.UniversalRent.request;
import lombok.Data;
import javax.validation.constraints.NotNull;


@Data
public class RenterRequest {

    @NotNull(message = "Renter email is required!")
    private String email;

    @NotNull(message = "Renter Card ID is required!")
    private String cardId;

    @NotNull(message = "Renter phone number email is required!")
    private String phoneNumber;

    @NotNull(message = "Renter first name is required!")
    private String firstName;

    @NotNull(message = "Renter last name is required!")
    private String lastName;

    @NotNull(message = "Renter age is required!")
    private Long age;

    @NotNull(message = "Renter license number is required!")
    private String licenseNumber;
}


