package com.insurance.database.service;

import com.insurance.config.adapter.web.persistence.CustomerPersistenceService;
import com.insurance.config.data.entity.CustomerEntity;
import com.insurance.config.data.entity.DriverEntity;
import com.insurance.database.model.CustomerModel;
import com.insurance.database.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerRepositoryService implements CustomerPersistenceService {

    private final CustomerRepository customerRepository;

    CustomerRepositoryService(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<CustomerEntity> findCustomerByDocument(String document) {
        Optional<CustomerModel> optionalCustomer = this.customerRepository.findByDocument(document);

        return optionalCustomer.flatMap(this::mountCustomer);

    }

    @Override
    public CustomerEntity create(String name, String document) {
        return mountCustomer(
                this.customerRepository.save(
                        CustomerModel
                                .builder()
                                .name(name)
                                .document(document)
                                .build())
        ).get();
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
