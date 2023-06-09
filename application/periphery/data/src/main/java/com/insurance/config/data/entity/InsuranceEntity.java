package com.insurance.config.data.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class InsuranceEntity {

    private Long id;
    private String uuid;
    private Double valueBudget;
    private int percentBudget;
    private CustomerEntity customer;
    private CarEntity car;
    private Boolean isActive;
    private LocalDateTime creationDate;
    private LocalDateTime updatedDate;


}
