package com.example.seller.controller;

import com.example.seller.dto.SellerCarDetailsRequest;
import com.example.seller.service.ManualAppraisalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/seller/appraisal/manual")
@RequiredArgsConstructor
public class ManualAppraisalController {
    private final ManualAppraisalService manualAppraisalService;

    @PostMapping
    public ResponseEntity<Long> createAppraisalManual(@RequestBody @Valid SellerCarDetailsRequest request) {
        Long response = manualAppraisalService.createManualAppraisal(request);
        return ResponseEntity.ok(response);
    }

}
