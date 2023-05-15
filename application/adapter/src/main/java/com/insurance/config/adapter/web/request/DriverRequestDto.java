package com.insurance.config.adapter.web.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DriverRequestDto {

    @Schema(description = "Driver Document (CPF).", example = "12345678912", required = true)
    private String document;

    @Schema(description = "Driver Birthdate.", example = "01/02/1990", required = true)
    private String birthdate;

    @Schema(description = "Main Driver.", example = "true", required = true)
    private Boolean isMainDriver;
}
