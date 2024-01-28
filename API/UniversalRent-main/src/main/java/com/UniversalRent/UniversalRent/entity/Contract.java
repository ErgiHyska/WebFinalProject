package com.UniversalRent.UniversalRent.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "contract")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "start_date", nullable = false)
    @JsonProperty("startDate")
    Date startDate;

    @Column(name = "end_date", nullable = false)
    @JsonProperty("endDate")
    Date endDate;

    @ManyToOne(cascade=CascadeType.REMOVE)
    @JoinColumn(name = "renter_id")
    private Renter renter;

    @ManyToOne(cascade=CascadeType.REMOVE)
    @JoinColumn(name = "lender_id")
    private Lender lender;

    @ManyToOne(cascade=CascadeType.REMOVE)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

}
