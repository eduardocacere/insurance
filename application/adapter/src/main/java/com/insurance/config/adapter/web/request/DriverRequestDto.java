package com.insurance.config.adapter.web.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DriverRequestDto {

    private String document;

    private String birthdate;

    private Boolean isMainDriver;
}
