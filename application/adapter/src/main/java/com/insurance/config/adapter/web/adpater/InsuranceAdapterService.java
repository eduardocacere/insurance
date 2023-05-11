package com.insurance.config.adapter.web.adpater;

import com.insurance.config.adapter.web.request.InsuranceRequestDto;
import com.insurance.config.adapter.web.response.InsuranceResponseDto;

public interface InsuranceAdapterService {

    InsuranceResponseDto create(InsuranceRequestDto insuranceRequest) throws Exception;
}
