package com.insurance.database.repository;

import com.insurance.database.model.ClaimModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepository extends JpaRepository<ClaimModel, Long> {
}
