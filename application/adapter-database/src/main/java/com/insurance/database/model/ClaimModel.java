package com.insurance.database.model;

import com.insurance.config.data.entity.ClaimEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(name = "claim")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClaimModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private CarModel car;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    private DriverModel driver;

    @Column(name = "event_date")
    private LocalDateTime eventDate;

    public ClaimEntity toEntity() {
        Optional<CarModel> optionalCar = Optional.ofNullable(this.car);

        return ClaimEntity
                .builder()
                .id(this.id)
                .car(optionalCar.orElse(CarModel.builder().build()).toEntity())
                .driver(this.getDriver().toEntity())
                .eventDate(this.eventDate)
                .build();
    }

}
