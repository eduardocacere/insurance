package com.insurance.config.adapter.web.request;

import lombok.Data;

import java.util.List;

@Data
public class InsuranceRequestDto {

    private String nameCustomer;
    private List<DriverRequestDto> driver;
    private CarRequestDto car;

}
