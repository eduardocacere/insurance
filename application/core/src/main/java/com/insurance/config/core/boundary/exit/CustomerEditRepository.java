package com.insurance.config.core.boundary.exit;

import com.insurance.config.core.domain.Customer;

public interface CustomerEditRepository {

    void create(Customer customer);

    void update(Customer customer);

    void delete(Customer.Id customerId);
}
