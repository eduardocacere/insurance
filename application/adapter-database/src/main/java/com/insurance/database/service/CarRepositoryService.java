package com.insurance.database.service;

import com.insurance.config.adapter.web.persistence.CarPersistenceService;
import com.insurance.config.adapter.web.request.CarRequestDto;
import com.insurance.config.data.entity.CarEntity;
import com.insurance.database.exception.DataBaseException;
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
                .plate(carModel.getPlate())
                .build();
    }

    @Override
    public CarEntity update(CarEntity carCurrent, CarRequestDto carUpdate) throws DataBaseException {
        return this.carRepository.findById(carCurrent.getId())
                .map(car -> this.updateCar(car, carUpdate))
                .orElseThrow(() -> new DataBaseException("Carro não encontrado."));
    }

    private CarEntity updateCar(CarModel carModel, CarRequestDto carRequest) {
        carModel.setModelCar(carRequest.getModel());
        carModel.setManufacture(carRequest.getManufacturer());
        carModel.setYear(carRequest.getYear());
        carModel.setFipeValue(carRequest.getFipeValue());
        carModel.setPlate(carRequest.getPlate());
        return this.carRepository.save(carModel).toEntity();
    }

    private CarModel createCar(CarEntity car) {
        return this.carRepository.save(CarModel
                .builder()
                .modelCar(car.getModel().toUpperCase())
                .manufacture(car.getManufacture().toUpperCase())
                .year(car.getYear())
                .fipeValue(car.getFipeValue())
                .plate(car.getPlate())
                .build());
    }
}
