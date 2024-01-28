package com.UniversalRent.UniversalRent.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "vehicle_lender"
        ,uniqueConstraints = @UniqueConstraint(columnNames = {"lender_card_id"})
)
public class Lender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long lenderId;

    @Column(name = "lender_card_id", nullable = false,unique = true)
    @JsonProperty("cardId")
    private String cardId;

    @Column(name = "Lender_email", nullable = false)
    @JsonProperty("email")
    private String email;

    @Column(name = "lender_phone_number", nullable = false)
    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @Column(name = "lender_first_name", nullable = false)
    @JsonProperty("firstName")
    private String firstName;

    @Column(name = "lender_last_name", nullable = false)
    @JsonProperty("lastName")
    private String lastName;

    @Column(name = "lender_age", nullable = false)
    @JsonProperty("age")
    private Long age;

    @JsonBackReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "lender",
            cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH} )
    private List<Contract> contracts;






}