package com.UniversalRent.UniversalRent.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class VehicleRequest {
    @Column(name="VIN",nullable = false)
    @JsonProperty("vin")
    private String vin;

    @NotNull(message = "Vehicle brand is required!")
    private String brand;

    @NotNull(message = "Model type is required!")
    private String modelType;

    @NotNull(message = "Vehicle type is required!")
    private String vehicleType;

    @NotNull(message = "Year of production is required!")
    private int yearOfProduction;

    @NotNull(message = "Engine type is required!")
    private String engineType;

    @NotNull(message = "Engine size is required!")
    private double engineSize;

    private double enginePower;
}
