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
@Table(name="vehicle_renter"
        ,uniqueConstraints = @UniqueConstraint(columnNames = {"renter_card_id","renter_license_number"})
)
public class Renter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long renterId;

    @Column(name="renter_email",nullable = false)
    @JsonProperty("email")
    private String email;

    @Column(name="renter_phone_number",nullable = false)
    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @Column(name="renter_first_name",nullable = false)
    @JsonProperty("firstName")
    private String firstName;

    @Column(name="renter_last_name",nullable = false)
    @JsonProperty("lastName")
    private String lastName;

    @Column(name="renter_age",nullable = false)
    @JsonProperty("age")
    private Long age;

    @Column(name="renter_license_number",nullable = false,unique = true)
    @JsonProperty("licenseNumber")
    private String licenseNumber;

    @Column(name="renter_card_id",nullable = false,unique = true)
    @JsonProperty("cardId")
    private String cardId;
    @JsonBackReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "renter",cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH} )
    private List<Contract> contracts;




}