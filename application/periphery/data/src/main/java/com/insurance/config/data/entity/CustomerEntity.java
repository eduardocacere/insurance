package com.insurance.config.data.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerEntity {


    private Long id;

    private String name;

    private String document;

    private DriverEntity driver;

}
