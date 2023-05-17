package com.insurance.database.service;

import com.insurance.config.adapter.web.persistence.DriverPersistenceService;
import com.insurance.config.adapter.web.request.DriverRequestDto;
import com.insurance.config.data.entity.DriverEntity;
import com.insurance.database.exception.DataBaseException;
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
            optionalDriver = Optional.of(this.create(document, birthdate)) ;
        }
        DriverModel driverModel = optionalDriver.get();
        return DriverEntity
                .builder()
                .id(driverModel.getId())
                .document(driverModel.getDocument())
                .birthdate(driverModel.getBirthdate())
                .build();
    }

    @Override
    public DriverEntity update(DriverEntity driverCurrent, DriverRequestDto driverRequest) throws DataBaseException {
        return this.driverRepository.findById(driverCurrent.getId())
                .map(driver -> this.createOrUpdate(driver, driverRequest))
                .orElseThrow(() -> new DataBaseException("Motorista não encontrado."));
    }

    @Override
    public DriverEntity createDriver(String document, LocalDate birthdate) {
        return this.create(document, birthdate).toEntity();
    }

    private DriverEntity createOrUpdate(DriverModel driver, DriverRequestDto driverRequest) {
        if(this.isNewDriver(driver.getDocument(), driverRequest.getDocument())) {
            return this.findOrCreate(driverRequest.getBirthdateToDate(), driverRequest.getDocument());
        }

        driver.setBirthdate(driverRequest.getBirthdateToDate());
        return this.driverRepository.save(driver).toEntity();
    }

    private boolean isNewDriver(String documentCurrent, String documentRequest) {
        return !documentCurrent.equals(documentRequest);
    }


    public DriverModel create(String document, LocalDate birthdate) {
        return this.driverRepository.save(DriverModel
                .builder()
                .birthdate(birthdate)
                .document(document)
                .build());
    }
}
