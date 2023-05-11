package com.insurance.config.adapter.web.persistence;

import com.insurance.config.data.entity.DriverEntity;

import java.time.LocalDate;

public interface DriverPersistenceService {

    DriverEntity findOrCreate(LocalDate birthdate, String document);


}
