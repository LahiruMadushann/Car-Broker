package com.example.buyer.service;

import com.example.buyer.dto.BuyerRegistrationRequest;

public interface BuyerService {
    Long createBuyer(BuyerRegistrationRequest buyerRegistrationRequest);
}
