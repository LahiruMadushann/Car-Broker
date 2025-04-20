package com.example.buyer.service;

import com.example.buyer.dto.BuyerRegistrationRequest;
import com.example.buyer.dto.BuyerRegistrationResponse;
import com.example.buyer.dto.MatchingConditionRequest;
import com.example.buyer.model.enums.Speciality;

import java.util.List;

public interface BuyerService {
    Long createBuyer(BuyerRegistrationRequest buyerRegistrationRequest);
    List<BuyerRegistrationResponse> getAllBuyers();
}
