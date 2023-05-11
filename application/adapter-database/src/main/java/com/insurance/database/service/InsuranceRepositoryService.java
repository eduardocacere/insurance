package com.insurance.database.service;

import com.insurance.config.adapter.web.persistence.InsurancePersistenceService;
import com.insurance.config.data.entity.InsuranceEntity;
import com.insurance.database.exception.DataBaseException;
import com.insurance.database.model.CarModel;
import com.insurance.database.model.CustomerModel;
import com.insurance.database.model.InsuranceModel;
import com.insurance.database.repository.CarRepository;
import com.insurance.database.repository.CustomerRepository;
import com.insurance.database.repository.InsuranceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class InsuranceRepositoryService implements InsurancePersistenceService {

    private InsuranceRepository insuranceRepository;

    private CarRepository carRepository;

    private CustomerRepository customerRepository;

    InsuranceRepositoryService(final InsuranceRepository insuranceRepository,
                               final CustomerRepository customerRepository,
                               final CarRepository carRepository) {
        this.insuranceRepository = insuranceRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }
    @Override
    public InsuranceEntity create(InsuranceEntity insuranceEntity) {
        Optional<CustomerModel> optionalCustomer = this.customerRepository.findByDocument(insuranceEntity.getCustomerModel().getDocument());

        Optional<CarModel> optionalCarModel = this.carRepository.findById(insuranceEntity.getCarModel().getId());

        return this.insuranceRepository.save(InsuranceModel
                .builder()
                    .uuid(UUID.randomUUID().toString())
                    .carModel(optionalCarModel.get())
                    .customerModel(optionalCustomer.get())
                    .valueBudget(insuranceEntity.getValueBudget())
                    .percentBudget(insuranceEntity.getPercentBudget())
                    .isActive(true)
                    .creationDate(LocalDateTime.now())
                .build()).toEntity();
    }

    @Override
    public InsuranceEntity findInsurance(String insuranceId) throws DataBaseException {
        return this.insuranceRepository.findByUuid(insuranceId).map(InsuranceModel::toEntity).orElseThrow(() -> new DataBaseException("Orçamento não encontrado."));
    }
}
