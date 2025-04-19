package com.example.buyer.controller;

import com.example.buyer.dto.BuyerRegistrationRequest;
import com.example.buyer.service.BuyerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/buyer")
@RequiredArgsConstructor
public class BuyerController {

    private final BuyerService buyerService;

    @PostMapping("/create")
    private ResponseEntity<Long> createBuyer(@RequestBody @Valid BuyerRegistrationRequest buyerRegistrationRequest) {
        Long response = buyerService.createBuyer(buyerRegistrationRequest);
        return ResponseEntity.ok(response);

    }
}
