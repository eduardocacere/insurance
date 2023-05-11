package com.insurance.config.data.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class DriverEntity {

    private Long id;
    private String name;
    private String document;
    private LocalDate birthdate;
    private CustomerEntity customer;
    private ClaimEntity claim;
    private List<CarDriverEntity> carDrivers;
}
