package com.insurance.database.model;

import com.insurance.config.data.entity.DriverEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "driver")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String document;

    @Column()
    private LocalDate birthdate;

    @OneToOne(mappedBy = "driver")
    private CustomerModel customer;

    @OneToOne(mappedBy = "driver")
    private ClaimModel claim;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "driver")
    private List<CarDriverModel> carDrivers;

    public DriverEntity toEntity() {
        return DriverEntity
                .builder()
                .id(this.getId())
                .document(this.getDocument())
                .birthdate(this.getBirthdate())
                .build();
    }

}
