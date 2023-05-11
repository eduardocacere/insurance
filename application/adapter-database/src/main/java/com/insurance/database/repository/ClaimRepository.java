package com.insurance.database.repository;

import com.insurance.database.model.ClaimModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClaimRepository extends JpaRepository<ClaimModel, Long> {

    @Query(value = "Select * from claim where car_id = :cardId", nativeQuery = true)
    Optional<ClaimModel> findByCardId(@Param("cardId") Long cardId);

    @Query(value = "Select * from claim where driver_id = :driverId", nativeQuery = true)
    Optional<ClaimModel> findByDriverId(@Param("driverId") Long cardId);
}
