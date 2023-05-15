package com.insurance.config.adapter.web.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class InsuranceRequestDto {

    @Schema(description = "Client Name .", example = "Jose", required = true)
    private String nameCustomer;

    @Schema(description = "Client Document (CPF).", example = "12345678912", required = true)
    private String documentCustomer;

    private DriverRequestDto driver;

    private CarRequestDto car;

}
