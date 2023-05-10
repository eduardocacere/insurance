package com.insurance.database.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "driver")
@Data
public class DriverModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String name;

    @Column(length = 200)
    private String document;

    @Column()
    private LocalDate birthdate;

    @OneToOne(mappedBy = "driver")
    private CustomerModel customer;

    @OneToOne(mappedBy = "driver")
    private ClaimModel claim;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "driver")
    private List<CarDriverModel> carDrivers;

}
