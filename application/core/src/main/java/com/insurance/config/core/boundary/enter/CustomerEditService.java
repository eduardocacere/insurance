package com.insurance.config.core.boundary.enter;

import com.insurance.config.core.domain.Customer;

public interface CustomerEditService {

    void create(Customer customer);

    void update(Customer customer);

    void delete(Customer.Id customerId);
}
