package com.insurance.config.data.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarDriverEntity {

    private Long id;
    private CarEntity car;
    private DriverEntity driver;
    private Boolean isMainDriver;
}
