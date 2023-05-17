package com.insurance.database.model;

import com.insurance.config.data.entity.CustomerEntity;
import com.insurance.config.data.entity.DriverEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "customer")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String document;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    private DriverModel driver;

    public CustomerEntity toEntity() {
        return CustomerEntity
                .builder()
                .id(id)
                .name(name)
                .document(document)
                .driver(Optional.ofNullable(driver).map(DriverModel::toEntity).orElse(null))
                .build();
    }

}
