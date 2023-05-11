package com.insurance.config.adapter.web.persistence;

import com.insurance.config.data.entity.CarDriverEntity;

public interface CarDriverPersistenceService {

    CarDriverEntity findOrCreate(CarDriverEntity carDriverEntity);
}
