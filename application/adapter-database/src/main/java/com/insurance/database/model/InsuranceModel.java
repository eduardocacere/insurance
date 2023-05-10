package com.insurance.database.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "insurance")
@Data
public class InsuranceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private CustomerModel customerModel;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private CarModel carModel;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "creation_dt")
    private LocalDateTime creationDate;

    @Column(name = "updated_at")
    private LocalDateTime updatedDate;


}
