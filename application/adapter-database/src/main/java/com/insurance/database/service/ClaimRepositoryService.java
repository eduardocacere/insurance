package com.insurance.database.service;

import com.insurance.config.adapter.web.persistence.ClaimPersistenceService;
import com.insurance.config.data.entity.CarEntity;
import com.insurance.config.data.entity.ClaimEntity;
import com.insurance.config.data.entity.DriverEntity;
import com.insurance.database.model.CarModel;
import com.insurance.database.model.ClaimModel;
import com.insurance.database.model.DriverModel;
import com.insurance.database.repository.CarRepository;
import com.insurance.database.repository.ClaimRepository;
import com.insurance.database.repository.DriverRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ClaimRepositoryService implements ClaimPersistenceService {

    private CarRepository carRepository;
    private DriverRepository driverRepository;
    private ClaimRepository claimRepository;

    ClaimRepositoryService(final ClaimRepository claimRepository,
                           final CarRepository carRepository,
                           final DriverRepository driverRepository) {

        this.claimRepository = claimRepository;
        this.carRepository = carRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    public Optional<ClaimEntity> findByCarOrDriver(CarEntity carEntity, DriverEntity driverEntity) {
        Optional<ClaimModel> optionalClaim = this.claimRepository.findByCardId(carEntity.getId());

        if(!optionalClaim.isPresent()) {
            optionalClaim = this.claimRepository.findByDriverId(driverEntity.getId());
        }

        return optionalClaim.map(ClaimModel::toEntity);

    }

    @Override
    public ClaimEntity create(CarEntity carEntity, DriverEntity driverEntity) {
        Optional<CarModel> optionalCarModel = this.carRepository.findById(carEntity.getId());
        Optional<DriverModel> optionalDriverModel = this.driverRepository.findById(driverEntity.getId());
        ClaimModel claimModel = ClaimModel
                .builder()
                .car(optionalCarModel.get())
                .driver(optionalDriverModel.get())
                .eventDate(LocalDateTime.now())
                .build();

        return this.claimRepository.save(claimModel).toEntity();
    }
}
