package com.insurance.database.model;

import com.insurance.config.data.entity.CarEntity;
import com.insurance.config.data.entity.ClaimEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "car")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model", length = 50)
    private String modelCar;

    @Column(length = 50)
    private String manufacture;

    @Column(length = 4)
    private int year;

    @Column(name = "fipe_value")
    private Float fipeValue;

    @Column()
    private String plate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car")
    private List<CarDriverModel> carDrivers;

    @OneToOne(mappedBy = "car")
    private ClaimModel claim;

    public CarEntity toEntity() {
        Optional<ClaimModel> optional = Optional.ofNullable(this.claim);

        return CarEntity
                .builder()
                .id(this.getId())
                .model(this.getModelCar())
                .manufacture(this.getManufacture())
                .year(this.getYear())
                .fipeValue(this.getFipeValue())
                .plate(this.plate)
                .build();
    }
}
