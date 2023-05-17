package com.insurance.database.service;

import com.insurance.config.adapter.web.persistence.CustomerPersistenceService;
import com.insurance.config.adapter.web.persistence.DriverPersistenceService;
import com.insurance.config.adapter.web.request.InsuranceRequestDto;
import com.insurance.config.data.entity.CustomerEntity;
import com.insurance.config.data.entity.DriverEntity;
import com.insurance.database.exception.DataBaseException;
import com.insurance.database.model.CustomerModel;
import com.insurance.database.model.DriverModel;
import com.insurance.database.repository.CustomerRepository;
import com.insurance.database.repository.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerRepositoryService implements CustomerPersistenceService {

    private final CustomerRepository customerRepository;

    private final DriverRepository driverRepository;

    private final DriverPersistenceService driverPersistenceService;

    CustomerRepositoryService(final CustomerRepository customerRepository,
                              final DriverRepository driverRepository,
                              final DriverPersistenceService driverPersistenceService) {
        this.customerRepository = customerRepository;
        this.driverRepository = driverRepository;
        this.driverPersistenceService = driverPersistenceService;
    }

    @Override
    public Optional<CustomerEntity> findCustomerByDocument(String document) {
        Optional<CustomerModel> optionalCustomer = this.customerRepository.findByDocument(document);

        return optionalCustomer.flatMap(this::mountCustomer);

    }

    @Override
    public CustomerEntity create(String name, String document, DriverEntity driverEntity) throws DataBaseException {
        DriverEntity driver = this.driverPersistenceService.findOrCreate(driverEntity.getBirthdate(), driverEntity.getDocument());
        DriverModel driverModel = this.driverRepository
                .findByDocument(driver.getDocument())
                .orElseThrow(() -> new DataBaseException("Cliente não encontrado."));
        return this.customerRepository.save(
                        CustomerModel
                                .builder()
                                .name(name)
                                .document(document)
                                .driver(driverModel)
                                .build())
                .toEntity()
        ;
    }

    @Override
    public CustomerEntity update(CustomerEntity customerCurrent, InsuranceRequestDto request) throws DataBaseException {
        return this.customerRepository.findById(customerCurrent.getId())
                .map(customerModel -> {
                    try {
                        return this.createOrUpdateCustomer(customerModel, request);
                    } catch (DataBaseException e) {
                        throw new RuntimeException(e);
                    }
                })
                .orElseThrow(() -> new DataBaseException("Cliente não encontrado."));

    }

    private CustomerEntity createOrUpdateCustomer(CustomerModel customerModel, InsuranceRequestDto request) throws DataBaseException {
        if(this.isNewCustomer(customerModel.getDocument(), request.getDocumentCustomer())) {
            this.create(request.getNameCustomer(), request.getDocumentCustomer(), request.getDriver().toEntity());
        }
        customerModel.setName(request.getNameCustomer());
        return this.customerRepository.save(customerModel).toEntity();
    }

    private boolean isNewCustomer(String document, String documentRequest) {
        return !document.equals(documentRequest);
    }

    private Optional<CustomerEntity> mountCustomer(CustomerModel customerModel) {
        return Optional.of(CustomerEntity
                .builder()
                .id(customerModel.getId())
                .name(customerModel.getName())
                .document(customerModel.getDocument())
                .build());
    }

}
