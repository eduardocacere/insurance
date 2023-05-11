package com.insurance.database.repository;

import com.insurance.database.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {

    Optional<CustomerModel> findByDocument(String document);
}
