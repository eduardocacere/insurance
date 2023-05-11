package com.insurance.database.repository;

import com.insurance.database.model.InsuranceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceRepository extends JpaRepository<InsuranceModel, Long> {
}
