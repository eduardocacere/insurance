package com.insurance.config.adapter.web.request;

import com.insurance.config.data.entity.DriverEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class DriverRequestDto {

    @Schema(description = "Driver Document (CPF).", example = "12345678912", required = true)
    private String document;

    @Schema(description = "Driver Birthdate.", example = "01/02/1990", required = true)
    private String birthdate;

    @Schema(description = "Main Driver.", example = "true", required = true)
    private Boolean isMainDriver;

    public LocalDate getBirthdateToDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(this.getBirthdate(), formatter);
    }

    public DriverEntity toEntity() {
        return DriverEntity
                .builder()
                .document(document)
                .birthdate(this.getBirthdateToDate())
                .build();
    }

}
