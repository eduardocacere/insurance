package com.insurance.config.adapter.web.request;

import lombok.Builder;
import lombok.Data;

@Data
public class ClaimRequestDto {

    private CarRequestDto car;
    private DriverRequestDto driver;
}
