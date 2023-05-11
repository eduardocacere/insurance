package com.insurance.database.repository;

import com.insurance.database.model.CarDriverModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarDriverRepository extends JpaRepository<CarDriverModel, Long> {

    @Query(value = "Select * from car_drive where car_id = :carId", nativeQuery = true)
    Optional<CarDriverModel> fincByCarId(@Param("carId") Long carId);

}
