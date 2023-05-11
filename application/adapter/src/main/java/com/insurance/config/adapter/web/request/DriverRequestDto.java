package com.insurance.config.adapter.web.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DriverRequestDto {

    private String document;
    private LocalDate birthdate;

    private Boolean isMainDriver;
}
