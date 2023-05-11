package com.insurance.config.adapter.web.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InsuranceResponseDto {

    private String requestId;

    private Double valueBudget;

    private int percentBudget;
}
