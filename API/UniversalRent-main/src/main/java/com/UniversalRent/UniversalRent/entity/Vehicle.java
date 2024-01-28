package com.UniversalRent.UniversalRent.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name="vehicle"
        ,uniqueConstraints = @UniqueConstraint(columnNames = {"VIN"})
)
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long vehicleId;

    @Column(name="VIN",nullable = false)
    @JsonProperty("vin")
    private String vin;

    @Column(name="brand",nullable = false)
    @JsonProperty("brand")
    private String brand;

    @Column(name="model_type",nullable = false)
    @JsonProperty("modelType")
    private String modelType;

    @Column(name="vehicle_type",nullable = false)
    @JsonProperty("vehicleType")
    private String vehicleType;

    @Column(name="year_of_production",nullable = false)
    @JsonProperty("yearOfProduction")
    private int yearOfProduction;

    @Column(name="engine_type",nullable = false)
    @JsonProperty("engineType")
    private String engineType;

    @Column(name="engine_size",nullable = false)
    @JsonProperty("engineSize")
    private double engineSize;

    @Column(name="price_per_day",nullable = false)
    @JsonProperty("pricePerDay")
    private double pricePerDay;

    @Column(name="engine_power")
    @JsonProperty("enginePower")
    private double enginePower;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "vehicle",cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH} )
    @JsonBackReference
    private List<Contract> contracts;

}
