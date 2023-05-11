package com.insurance.core.usecase;

import com.insurance.config.adapter.web.adpater.ClaimAdpaterService;
import com.insurance.config.adapter.web.persistence.CarPersistenceService;
import com.insurance.config.adapter.web.persistence.ClaimPersistenceService;
import com.insurance.config.adapter.web.persistence.DriverPersistenceService;
import com.insurance.config.adapter.web.request.ClaimRequestDto;
import com.insurance.config.data.entity.CarEntity;
import com.insurance.config.data.entity.DriverEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class ClaimUseCase implements ClaimAdpaterService {

    private DriverPersistenceService driverPersistenceService;

    private CarPersistenceService carPersistenceService;

    private ClaimPersistenceService claimPersistenceService;

    ClaimUseCase(final DriverPersistenceService driverPersistenceService,
                 final CarPersistenceService carPersistenceService,
                 final ClaimPersistenceService claimPersistenceService) {
        this.carPersistenceService = carPersistenceService;
        this.driverPersistenceService = driverPersistenceService;
        this.claimPersistenceService = claimPersistenceService;

    }
    @Override
    public String registerclaim(ClaimRequestDto claimRequest) {
        CarEntity carEntity = this.carPersistenceService.findOrCreate(claimRequest.getCar().toEntity());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birthdate = LocalDate.parse(claimRequest.getDriver().getBirthdate(), formatter);
        DriverEntity driverEntity = this.driverPersistenceService.findOrCreate(birthdate, claimRequest.getDriver().getDocument());

        this.claimPersistenceService.create(carEntity, driverEntity);

        return "Sinistro registrado com sucesso";
    }
}
