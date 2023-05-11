package com.insurance.config.adapter.web.adpater;

import com.insurance.config.adapter.web.request.ClaimRequestDto;

import javax.xml.ws.ServiceMode;

public interface ClaimAdpaterService {

    String registerclaim(ClaimRequestDto claimRequest);

}
