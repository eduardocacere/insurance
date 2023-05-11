package com.insurance.config.adapter.web.adpater;

import com.insurance.config.adapter.web.request.InsuranceRequestDto;
import com.insurance.config.adapter.web.response.InsuranceResponseDto;

public interface InsuranceAdapterService {

    InsuranceResponseDto create(InsuranceRequestDto insuranceRequest) throws Exception;

    InsuranceResponseDto getInsurance(String insuranceId) throws Exception;

    InsuranceResponseDto update(InsuranceRequestDto requestDto, String insuranceId) throws Exception;

    InsuranceResponseDto delete(String insuranceId) throws Exception;
}
