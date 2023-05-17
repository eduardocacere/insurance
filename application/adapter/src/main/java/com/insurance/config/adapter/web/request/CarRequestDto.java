package com.insurance.config.adapter.web.request;

import com.insurance.config.data.entity.CarEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CarRequestDto {

    @Schema(description = "Car Model.", example = "Wrv", required = true)
    private String model;

    @Schema(description = "Car Manufacturer.", example = "Honda", required = true)
    private String manufacturer;

    @Schema(description = "Car Year.", example = "2018", required = true)
    private int year;

    @Schema(description = "Car Value Fipe.", example = "50000", required = true)
    private Float fipeValue;

    @Schema(description = "Car plate.", example = "ABC1234", required = true)
    private String plate;

    public CarEntity toEntity() {
        return CarEntity
                .builder()
                .model(model)
                .manufacture(manufacturer)
                .year(year)
                .fipeValue(fipeValue)
                .plate(plate)
                .build();
    }

}
