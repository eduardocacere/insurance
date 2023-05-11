package com.insurance.database.repository;

import com.insurance.database.model.DriverModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<DriverModel, Long> {

    Optional<DriverModel> findByDocument(String document);
}
