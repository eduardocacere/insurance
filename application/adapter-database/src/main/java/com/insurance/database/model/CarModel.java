package com.insurance.database.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "car")
@Data
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String model;

    @Column(length = 50)
    private String manufacture;

    @Column(length = 4)
    private int year;

    @Column(name = "fipe_value")
    private Float fipeValue;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car")
    private List<CarDriverModel> carDrivers;

    @OneToOne(mappedBy = "car")
    private ClaimModel claim;

}
