package com.insurance.database.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "car_drive")
@Data
public class CarDriverModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private CarModel car;

    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    private DriverModel driver;

    @Column(name = "is_main_driver")
    private Boolean isMainDriver;

}
