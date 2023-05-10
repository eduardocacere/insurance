package com.insurance.database.repository;

import com.insurance.database.model.DriverModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<DriverModel, Long> {
}
