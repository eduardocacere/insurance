package com.insurance.config.web.controller;

import com.insurance.config.adapter.web.adpater.ClaimAdpaterService;
import com.insurance.config.adapter.web.persistence.CarPersistenceService;
import com.insurance.config.adapter.web.persistence.DriverPersistenceService;
import com.insurance.config.adapter.web.request.ClaimRequestDto;
import com.insurance.config.adapter.web.response.InsuranceResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/claim")
@RestController
public class ClaimController {

    private ClaimAdpaterService claimAdpaterService;

    ClaimController(final ClaimAdpaterService claimAdpaterService) {
        this.claimAdpaterService = claimAdpaterService;
    }
    @PostMapping
    public ResponseEntity<String> registerClaim(@RequestBody ClaimRequestDto claimRequest) {
        return ResponseEntity.ok(this.claimAdpaterService.registerclaim(claimRequest));

    }
}
