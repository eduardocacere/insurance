package com.insurance.config.core.usecase;

import com.insurance.config.core.boundary.enter.CustomerEditService;
import com.insurance.config.core.boundary.exit.CustomerEditRepository;
import com.insurance.config.core.domain.Customer;

import static org.apache.commons.lang3.Validate.notNull;

public class CustomerEditUseCase implements CustomerEditService {

    private CustomerEditRepository customerEditRepository;

    public CustomerEditUseCase(final CustomerEditRepository customerEditRepository) {
        this.customerEditRepository = notNull(customerEditRepository);
    }

    @Override
    public void create(final Customer customer) {
        notNull(customer);

        customerEditRepository.create(customer);
    }

    @Override
    public void update(final Customer customer) {
        notNull(customer);

        customerEditRepository.update(customer);
    }

    @Override
    public void delete(final Customer.Id customerId) {
        notNull(customerId);

        customerEditRepository.delete(customerId);
    }
}
