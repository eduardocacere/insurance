package com.insurance.database.repository;

import com.insurance.database.model.CarDriverModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarDriverRepository extends JpaRepository<CarDriverModel, Long> {
}
