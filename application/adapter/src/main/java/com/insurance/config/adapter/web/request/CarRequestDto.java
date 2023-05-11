package com.insurance.config.adapter.web.request;

import com.insurance.config.data.entity.CarEntity;
import lombok.Data;

@Data
public class CarRequestDto {

    private String model;

    private String manufacturer;

    private int year;

    private Float fipeValue;

    public CarEntity toEntity() {
        return CarEntity
                .builder()
                .model(model)
                .manufacture(manufacturer)
                .year(year)
                .fipeValue(fipeValue)
                .build();
    }

}
