package com.insurance.config.web.controller;


import com.insurance.config.adapter.web.adpater.InsuranceAdapterService;
import com.insurance.config.adapter.web.request.InsuranceRequestDto;
import com.insurance.config.adapter.web.response.InsuranceResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(
            summary = "Create quote",
            description = "Endpoint responsible for creating vehicle budget",
            tags = { "Insurance"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Request successfully completed."),
                    @ApiResponse(responseCode = "400", description = "Error when performing the request."),
                    @ApiResponse(responseCode = "500", description = "Business rule error.")
            }
    )
    @PostMapping
    public ResponseEntity<InsuranceResponseDto> create(@RequestBody InsuranceRequestDto requestDto) throws Exception {
        return ResponseEntity.ok().body(this.service.create(requestDto));
    }

    @Operation(
            summary = "Search quote",
            description = "Endpoint responsible for querying the vehicle budget",
            tags = { "Insurance"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Request successfully completed."),
                    @ApiResponse(responseCode = "400", description = "Error when performing the request."),
                    @ApiResponse(responseCode = "500", description = "Business rule error.")
            }
    )
    @GetMapping("/{insuranceId}")
    public ResponseEntity<InsuranceResponseDto> getInsurance(@PathVariable String insuranceId) throws Exception {
        return ResponseEntity.ok().body(this.service.getInsurance(insuranceId));
    }

    @Operation(
            summary = "Update quote",
            description = "Endpoint responsible for updating the vehicle budget.",
            tags = { "Insurance"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Request successfully completed."),
                    @ApiResponse(responseCode = "400", description = "Error when performing the request."),
                    @ApiResponse(responseCode = "500", description = "Business rule error.")
            }
    )
    @PutMapping("/{insuranceId}")
    public ResponseEntity<InsuranceResponseDto> update(@RequestBody InsuranceRequestDto requestDto, @PathVariable String insuranceId) throws Exception {
        return ResponseEntity.ok().body(this.service.update(requestDto, insuranceId));
    }
    @Operation(
            summary = "Delete quote",
            description = "Endpoint responsible for deleting  the vehicle budget.",
            tags = { "Insurance"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Request successfully completed."),
                    @ApiResponse(responseCode = "400", description = "Error when performing the request."),
                    @ApiResponse(responseCode = "500", description = "Business rule error.")
            }
    )
    @DeleteMapping("/{insuranceId}")
    public ResponseEntity<InsuranceResponseDto> delete(@PathVariable String insuranceId) throws Exception {
        return ResponseEntity.ok().body(this.service.delete(insuranceId));
    }
}
