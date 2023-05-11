package com.insurance.config.web.controller;


import com.insurance.config.adapter.web.adpater.InsuranceAdapterService;
import com.insurance.config.adapter.web.request.InsuranceRequestDto;
import com.insurance.config.adapter.web.response.InsuranceResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/insurance/budget")
@RestController
@Tag(name = "Insurance", description = "Api responsible for managing the budget")
public class InsuranceController {


    private InsuranceAdapterService service;

    public InsuranceController(final InsuranceAdapterService insuranceAdapterService) {
        this.service = insuranceAdapterService;
    }


    @PostMapping
    public ResponseEntity<InsuranceResponseDto> create(@RequestBody InsuranceRequestDto requestDto) {
        return ResponseEntity.ok().body(this.service.create(requestDto));
    }


}
