package com.example.buyer.service;

import com.example.buyer.dto.BuyerRegistrationRequest;
import com.example.buyer.dto.BuyerRegistrationResponse;

import java.util.List;

public interface BuyerService {
    Long createBuyer(BuyerRegistrationRequest buyerRegistrationRequest);
    List<BuyerRegistrationResponse> getAllBuyers();
}
