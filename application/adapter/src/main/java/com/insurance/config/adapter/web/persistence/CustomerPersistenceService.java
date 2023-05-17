package com.insurance.config.adapter.web.persistence;

import com.insurance.config.adapter.web.request.InsuranceRequestDto;
import com.insurance.config.data.entity.CustomerEntity;
import com.insurance.config.data.entity.DriverEntity;

import java.util.Optional;

public interface CustomerPersistenceService {

    Optional<CustomerEntity> findCustomerByDocument(String document);

    CustomerEntity create(String name, String document, DriverEntity driverEntity) throws Exception;

    CustomerEntity update(CustomerEntity customerCurrent, InsuranceRequestDto name) throws Exception;
}
