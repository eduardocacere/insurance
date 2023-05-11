package com.insurance.config.data.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClaimEntity {

    private Long id;
    private CarEntity car;
    private DriverEntity driver;
    private LocalDateTime eventDate;
}
