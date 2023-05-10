package com.insurance.config.core.boundary.enter;

import com.insurance.config.core.domain.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> customers();

    Customer customer(Customer.Id customerId);
}
