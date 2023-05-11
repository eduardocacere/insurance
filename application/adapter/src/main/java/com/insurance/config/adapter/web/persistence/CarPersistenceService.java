package com.insurance.config.adapter.web.persistence;

import com.insurance.config.data.entity.CarEntity;

public interface CarPersistenceService {

    CarEntity findOrCreate(CarEntity car);
}
