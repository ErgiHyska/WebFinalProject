package com.UniversalRent.UniversalRent.request;

import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * DTO class
 */
@Data
public class LenderRequest {

    @NotNull(message = "Lender email is required!")
    private String email;

    @NotNull(message = "Lender phone number email is required!")
    private String phoneNumber;

    @NotNull(message = "Lender Card ID is required!")
    private String cardId;

    @NotNull(message = "Lender first name is required!")
    private String firstName;

    @NotNull(message = "Lender last name is required!")
    private String lastName;

    @NotNull(message = "Lender age is required!")
    private Long age;
}