package com.insurance.config.core.boundary.exit;

import com.insurance.config.core.domain.Customer;

import java.util.List;

public interface CustomerRepository {

    List<Customer> customers();

    Customer customer(Customer.Id customerId);
}
