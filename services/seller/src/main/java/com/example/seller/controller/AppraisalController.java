package com.example.seller.controller;

import com.example.seller.dto.SellerCarDetailsRequest;
import com.example.seller.service.AppraisalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/seller/appraisal/auto")
@RequiredArgsConstructor
public class AppraisalController {

    private final AppraisalService appraisalService;

    @PostMapping
    public ResponseEntity<String> createAppraisal(@RequestBody @Valid SellerCarDetailsRequest request) {
        String requestId = appraisalService.queueAppraisalRequest(request);
        return ResponseEntity.accepted().body(requestId);
    }
}
