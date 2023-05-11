package com.insurance.database.model;

import com.insurance.config.data.entity.CarDriverEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "car_drive")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarDriverModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private CarModel car;

    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    private DriverModel driver;

    @Column(name = "is_main_driver")
    private Boolean isMainDriver;

    public CarDriverEntity toEntity() {
        return CarDriverEntity
                .builder()
                .id(this.getId())
                .car(this.car.toEntity())
                .driver(this.toEntity().getDriver())
                .isMainDriver(this.isMainDriver)
                .build();
    }
}
