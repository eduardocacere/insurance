package com.insurance.database.model;

import com.insurance.config.data.entity.InsuranceEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "insurance")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsuranceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String uuid;

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

    public InsuranceEntity toEntity() {
        return InsuranceEntity
                .builder()
                .id(id)
                .uuid(uuid)
                .carModel(carModel.toEntity())
                .isActive(isActive)
                .creationDate(creationDate)
                .updatedDate(updatedDate)
                .build();
    }

}
