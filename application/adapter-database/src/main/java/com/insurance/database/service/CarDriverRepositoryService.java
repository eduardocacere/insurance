package com.insurance.database.service;

import com.insurance.config.adapter.web.persistence.CarDriverPersistenceService;
import com.insurance.config.data.entity.CarDriverEntity;
import com.insurance.database.model.CarDriverModel;
import com.insurance.database.model.CarModel;
import com.insurance.database.model.DriverModel;
import com.insurance.database.repository.CarDriverRepository;
import com.insurance.database.repository.CarRepository;
import com.insurance.database.repository.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarDriverRepositoryService implements CarDriverPersistenceService {

    private CarDriverRepository carDriverRepository;
    private CarRepository carRepository;
    private DriverRepository driverRepository;

    CarDriverRepositoryService(final CarDriverRepository carDriverRepository,
                               final CarRepository carRepository,
                               final DriverRepository driverRepository) {

        this.carDriverRepository = carDriverRepository;
        this.carRepository = carRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    public CarDriverEntity findOrCreate(CarDriverEntity carDriverEntity) {

        Optional<CarDriverModel> optionalCarDriverModel = this.carDriverRepository.fincByCarId(carDriverEntity.getCar().getId());

        if(!optionalCarDriverModel.isPresent()) {
            optionalCarDriverModel = Optional.of(this.create(carDriverEntity));
        }

        CarDriverModel carDriverModel = optionalCarDriverModel.get();
        return CarDriverEntity
                .builder()
                .id(carDriverModel.getId())
                .car(carDriverModel.getCar().toEntity())
                .driver(carDriverModel.getDriver().toEntity())
                .isMainDriver(carDriverEntity.getIsMainDriver())
                .build();
    }

    private CarDriverModel create(CarDriverEntity carDriverEntity) {
        Optional<CarModel> optionalCarModel = this.carRepository.findById(carDriverEntity.getCar().getId());
        Optional<DriverModel> optionalDriverModel = this.driverRepository.findById(carDriverEntity.getDriver().getId());

        return this.carDriverRepository.save(
                CarDriverModel
                        .builder()
                        .car(optionalCarModel.get())
                        .driver(optionalDriverModel.get())
                        .isMainDriver(carDriverEntity.getIsMainDriver())
                        .build()
                );
    }
}
