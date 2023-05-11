package com.insurance.config.data.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CarEntity {

    private Long id;
    private String model;
    private String manufacture;
    private int year;
    private Float fipeValue;
    private String plate;
    private ClaimEntity claim;

}
