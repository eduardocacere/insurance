package com.insurance.config.adapter.web.persistence;

import com.insurance.config.adapter.web.request.CarRequestDto;
import com.insurance.config.data.entity.CarEntity;

public interface CarPersistenceService {

    CarEntity findOrCreate(CarEntity car);

    CarEntity update(CarEntity carCurrent, CarRequestDto carUpdate) throws Exception;
}
