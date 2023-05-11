package com.insurance.config.adapter.web.request;

import lombok.Data;

@Data
public class CarRequestDto {

    private String model;

    private String manufacturer;

    private String year;

    private String fipeValue;
}
