package com.insurance.core.usecase;

import com.insurance.config.adapter.web.adpater.InsuranceAdapterService;
import com.insurance.config.adapter.web.request.InsuranceRequestDto;
import com.insurance.config.adapter.web.response.InsuranceResponseDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class InsuranceUseCase implements InsuranceAdapterService {

    Logger logger = LoggerFactory.getLogger(InsuranceUseCase.class);

    InsuranceUseCase() {

    }
    @Override
    public InsuranceResponseDto create(InsuranceRequestDto insuranceRequest) {
        logger.info("Criando o Insurance");
        return null;
    }
}
