package com.insurance.config.adapter.web.persistence;

import com.insurance.config.data.entity.CustomerEntity;

import java.util.Optional;

public interface CustomerPersistenceService {

    Optional<CustomerEntity> findCustomerByDocument(String document);

    CustomerEntity create(String name, String document);
}
