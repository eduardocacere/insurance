package com.insurance.config.adapter.web.persistence;

import com.insurance.config.data.entity.CarDriverEntity;
import com.insurance.config.data.entity.CarEntity;
import com.insurance.config.data.entity.ClaimEntity;
import com.insurance.config.data.entity.DriverEntity;

import java.util.Optional;

public interface ClaimPersistenceService {

    Optional<ClaimEntity> findByCarOrDriver(CarEntity carEntity, DriverEntity driverEntity);

    ClaimEntity create(CarEntity carEntity, DriverEntity driverEntity);
}
