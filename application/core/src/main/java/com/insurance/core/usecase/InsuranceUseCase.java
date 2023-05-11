package com.insurance.core.usecase;

import com.insurance.config.adapter.web.adpater.InsuranceAdapterService;
import com.insurance.config.adapter.web.persistence.*;
import com.insurance.config.adapter.web.request.DriverRequestDto;
import com.insurance.config.adapter.web.request.InsuranceRequestDto;
import com.insurance.config.adapter.web.response.InsuranceResponseDto;

import com.insurance.config.data.entity.*;
import com.insurance.core.usecase.exception.InsuranceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;


@Service
public class InsuranceUseCase implements InsuranceAdapterService {
    Logger logger = LoggerFactory.getLogger(InsuranceUseCase.class);

    private CustomerPersistenceService persistenceService;
    private DriverPersistenceService driverPersistenceService;

    private CarPersistenceService carPersistenceService;

    private ClaimPersistenceService claimPersistenceService;

    private CarDriverPersistenceService carDriverPersistenceService;
    private InsurancePersistenceService insurancePersistenceService;

    InsuranceUseCase(final CustomerPersistenceService persistenceService,
                     final DriverPersistenceService driverPersistenceService,
                     final CarPersistenceService carPersistenceService,
                     final CarDriverPersistenceService carDriverPersistenceService,
                     final ClaimPersistenceService claimPersistenceService,
                     final InsurancePersistenceService insurancePersistenceService) {
        this.persistenceService = persistenceService;
        this.driverPersistenceService = driverPersistenceService;
        this.carPersistenceService = carPersistenceService;
        this.carDriverPersistenceService = carDriverPersistenceService;
        this.claimPersistenceService = claimPersistenceService;
        this.insurancePersistenceService = insurancePersistenceService;
    }
    @Override
    public InsuranceResponseDto create(InsuranceRequestDto insuranceRequest) throws InsuranceException {
        logger.info("Criando o Insurance");

        Optional<CustomerEntity> optionalCustomer = this.persistenceService.findCustomerByDocument(insuranceRequest.getDocumentCustomer());
        CustomerEntity customer = this.createCustomerIfnotExist(optionalCustomer, insuranceRequest);
        return this.processDriver(insuranceRequest.getDriver(), insuranceRequest, customer);

    }

    private InsuranceResponseDto processDriver(DriverRequestDto driver, InsuranceRequestDto insuranceRequest, CustomerEntity customer) throws InsuranceException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birthdate = LocalDate.parse(driver.getBirthdate(), formatter);

        DriverEntity driverEntiry = this.driverPersistenceService.findOrCreate(birthdate, driver.getDocument());

        CarEntity carEntity = this.carPersistenceService.findOrCreate(
                CarEntity
                    .builder()
                        .model(insuranceRequest.getCar().getModel())
                        .manufacture(insuranceRequest.getCar().getManufacturer())
                        .year(insuranceRequest.getCar().getYear())
                        .fipeValue(insuranceRequest.getCar().getFipeValue())
                    .build());

        CarDriverEntity carDriverEntity = this.carDriverPersistenceService.findOrCreate(
                CarDriverEntity
                        .builder()
                        .car(carEntity)
                        .driver(driverEntiry)
                        .isMainDriver(driver.getIsMainDriver())
                        .build()
        );

        InsuranceEntity insuranceEntity = this.insurancePersistenceService.create(InsuranceEntity
                .builder()
                .carModel(carEntity)
                .customerModel(customer)
                .build());

        InsuranceResponseDto insurance = this.engineInsurance(driverEntiry, carEntity, carDriverEntity);
        insurance.setRequestId(insuranceEntity.getUuid());
        return insurance;
    }

    private InsuranceResponseDto engineInsurance(DriverEntity driverEntiry, CarEntity carEntity, CarDriverEntity carDriverEntity) throws InsuranceException {

        int percentBudget = 6;
        double value = carEntity.getFipeValue() * 0.06;

        InsuranceResponseDto insurance = InsuranceResponseDto
                .builder()
                .valueBudget(carEntity.getFipeValue().doubleValue() + value)
                .percentBudget(percentBudget)
                .build();

        Optional<ClaimEntity> optionalClaim = this.claimPersistenceService.findByCarOrDriver(carEntity, driverEntiry);
        this.driverAgeRisk(driverEntiry.getBirthdate(), insurance, carEntity.getFipeValue());
        this.calculateClaimCar(optionalClaim, carEntity, carEntity.getFipeValue(), insurance);
        this.calculateClaimDriver(optionalClaim, driverEntiry, carEntity.getFipeValue(), insurance);

        return insurance;
    }

    private void driverAgeRisk(LocalDate birthdate, InsuranceResponseDto insurance, Float fipeValue) {
        int age = this.ageDriver(birthdate);
        if(this.isAgeRisck(age)) {
            int percentBudget = insurance.getPercentBudget() + 2;
            Double value = fipeValue * 0.02;
            insurance.setPercentBudget(percentBudget);
            insurance.setValueBudget(insurance.getValueBudget() + value);
        }
    }

    private boolean isAgeRisck(int age) {
        return (age > 18) & (age < 25);
    }

    private int ageDriver(LocalDate birthdate) {

        ZonedDateTime zonedDateTime = birthdate.atStartOfDay(ZoneId.systemDefault());
        Instant instant = zonedDateTime.toInstant();
        Date dateBirthdate = Date.from(instant);

        Calendar cData = Calendar.getInstance();
        Calendar today= Calendar.getInstance();
        cData.setTime(dateBirthdate);
        cData.set(Calendar.YEAR, today.get(Calendar.YEAR));
        int age = cData.after(today) ? -1 : 0;
        cData.setTime(dateBirthdate);
        age += today.get(Calendar.YEAR) - cData.get(Calendar.YEAR);
        return age;
    }

    private void calculateClaimDriver(Optional<ClaimEntity> optionalClaim, DriverEntity driverEntiry, Float fipeValue, InsuranceResponseDto insurance) {
        if(!optionalClaim.isPresent()) {
            return;
        }
        ClaimEntity claimEntity = optionalClaim.get();

        if(this.isCalculeClaim(claimEntity.getDriver().getId(), driverEntiry.getId())) {
            int percentBudget = insurance.getPercentBudget() + 2;
            Double value = fipeValue * 0.02;
            insurance.setPercentBudget(percentBudget);
            insurance.setValueBudget(insurance.getValueBudget() + value);
        }
    }

    private void calculateClaimCar(Optional<ClaimEntity> optionalClaim, CarEntity carEntity, Float fipeValue, InsuranceResponseDto insurance) throws InsuranceException {
        if(!optionalClaim.isPresent()) {
            return;
        }
        ClaimEntity claimEntity = optionalClaim.get();

        if(this.isCalculeClaim(claimEntity.getCar().getId(), carEntity.getId())) {
            throw new InsuranceException("Atenção carro possui restrição");
        }
    }

    private boolean isCalculeClaim(Long idClaimCard, Long idCard) {
        return Objects.equals(idClaimCard, idCard);
    }


    private CustomerEntity createCustomerIfnotExist(Optional<CustomerEntity> optionalCustomer, InsuranceRequestDto insuranceRequest) {
        return optionalCustomer.orElseGet(() -> this.persistenceService.create(insuranceRequest.getNameCustomer(), insuranceRequest.getDocumentCustomer()));

    }
}
