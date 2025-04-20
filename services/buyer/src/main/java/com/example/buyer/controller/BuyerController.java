package com.example.buyer.controller;

import com.example.buyer.dto.BuyerRegistrationRequest;
import com.example.buyer.dto.BuyerRegistrationResponse;
import com.example.buyer.dto.MatchingConditionRequest;
import com.example.buyer.dto.MatchingConditionResponse;
import com.example.buyer.model.enums.Speciality;
import com.example.buyer.service.BuyerService;
import com.example.buyer.service.MatchingConditionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/buyer")
@RequiredArgsConstructor
public class BuyerController {

    private final BuyerService buyerService;
    private final MatchingConditionService matchingConditionService;

    @PostMapping("/create")
    public ResponseEntity<Long> createBuyer(@RequestBody @Valid BuyerRegistrationRequest buyerRegistrationRequest) {
        Long response = buyerService.createBuyer(buyerRegistrationRequest);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/all")
    public ResponseEntity<List<BuyerRegistrationResponse>> getAllBuyers() {
        var response = buyerService.getAllBuyers();
        return ResponseEntity.ok(response);

    }

    @PutMapping("/{buyerId}/{speciality}/matching-conditions")
    public ResponseEntity<Long> registerMatchingConditions(
            @PathVariable Long buyerId,
            @PathVariable Speciality speciality,
            @RequestBody @Valid MatchingConditionRequest matchingConditionRequest) {

        Long response = matchingConditionService.createMatchingConditions(buyerId, speciality, matchingConditionRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/matching-conditions")
    public ResponseEntity<List<MatchingConditionResponse>> getAllMatchingConditions() {
        var response = matchingConditionService.getAllMatchingConditions();
        return ResponseEntity.ok(response);

    }
}
