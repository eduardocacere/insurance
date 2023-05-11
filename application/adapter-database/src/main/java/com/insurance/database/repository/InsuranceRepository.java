package com.insurance.database.repository;

import com.insurance.database.model.InsuranceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.crypto.spec.OAEPParameterSpec;
import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface InsuranceRepository extends JpaRepository<InsuranceModel, Long> {

    Optional<InsuranceModel> findByUuid(String uuid);
}
