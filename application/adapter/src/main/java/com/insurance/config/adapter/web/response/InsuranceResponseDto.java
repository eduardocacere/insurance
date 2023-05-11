package com.insurance.config.adapter.web.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InsuranceResponseDto {

    private Float valueBudget;

    private int percentBudget;
}