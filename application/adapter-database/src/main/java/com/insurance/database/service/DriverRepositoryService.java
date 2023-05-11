package com.insurance.database.service;

import com.insurance.config.adapter.web.persistence.DriverPersistenceService;
import com.insurance.config.data.entity.DriverEntity;
import com.insurance.database.model.DriverModel;
import com.insurance.database.repository.DriverRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class DriverRepositoryService implements DriverPersistenceService {

    private DriverRepository driverRepository;
    DriverRepositoryService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }
    @Override
    public DriverEntity findOrCreate(LocalDate birthdate, String document) {

        Optional<DriverModel> optionalDriver = this.driverRepository.findByDocument(document);

        if(!optionalDriver.isPresent()) {
            optionalDriver = Optional.of(this.createDriver(document, birthdate)) ;
        }
        DriverModel driverModel = optionalDriver.get();
        return DriverEntity
                .builder()
                .id(driverModel.getId())
                .document(driverModel.getDocument())
                .birthdate(driverModel.getBirthdate())
                .build();
    }

    private DriverModel createDriver(String document, LocalDate birthdate) {
        return this.driverRepository.save(DriverModel
                .builder()
                .birthdate(birthdate)
                .document(document)
                .build());
    }
}
