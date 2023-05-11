package com.insurance.database.service;

import com.insurance.config.adapter.web.persistence.CarPersistenceService;
import com.insurance.config.data.entity.CarEntity;
import com.insurance.database.model.CarModel;
import com.insurance.database.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarRepositoryService implements CarPersistenceService {

    private CarRepository carRepository;
    CarRepositoryService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    @Override
    public CarEntity findOrCreate(CarEntity car) {

        Optional<CarModel> optionalCarModel = this.carRepository.findByModelCarAndManufactureAndYear(car.getModel().toUpperCase(), car.getManufacture().toUpperCase(), car.getYear());

        if(!optionalCarModel.isPresent()) {
            optionalCarModel =  Optional.of(this.createCar(car));
        }

        CarModel carModel = optionalCarModel.get();
        return CarEntity
                .builder()
                .id(carModel.getId())
                .model(carModel.getModelCar())
                .manufacture(carModel.getManufacture())
                .year(carModel.getYear())
                .fipeValue(carModel.getFipeValue())
                .build();
    }

    private CarModel createCar(CarEntity car) {
        return this.carRepository.save(CarModel
                .builder()
                .modelCar(car.getModel().toUpperCase())
                .manufacture(car.getManufacture().toUpperCase())
                .year(car.getYear())
                .fipeValue(car.getFipeValue())
                .build());
    }
}
