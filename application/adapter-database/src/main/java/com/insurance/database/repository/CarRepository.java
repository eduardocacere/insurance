package com.insurance.database.repository;

import com.insurance.database.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<CarModel, Long> {

    // IgnoreCase
    Optional<CarModel> findByModelCarAndManufactureAndYear(String model, String manufacture, int year);

    Optional<CarModel> findByModelCarIgnoreCase(String model);

    CarModel findByManufactureIgnoreCase(String manufacture);

}
