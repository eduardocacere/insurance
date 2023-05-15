package com.insurance.config.adapter.web.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InsuranceResponseDto {

    @Schema(description = "insurance Id.", example = "8b9c1c27-62e9-4e2a-b64d-904d25f2d806", required = true)
    private String insuranceId;

    @Schema(description = "insurance Value.", example = "55000", required = true)
    private Double valueBudget;

    @Schema(description = "insurance Porcent.", example = "10", required = true)
    private int percentBudget;


}
