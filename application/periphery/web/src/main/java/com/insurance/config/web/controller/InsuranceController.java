package com.insurance.config.web.controller;

import com.insurance.config.adapter.web.api.InsuranceResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/insurance/budget")
@RestController
public class InsuranceController {


    public ResponseEntity<InsuranceResponseDto> create() {
        return ResponseEntity.ok().body(new InsuranceResponseDto());
    }


}
