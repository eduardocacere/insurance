package com.insurance.config.adapter.web.persistence;

import com.insurance.config.data.entity.InsuranceEntity;

public interface InsurancePersistenceService {

    InsuranceEntity create(InsuranceEntity insuranceEntity);
}
