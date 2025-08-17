package com.example.buyer.service;

import com.example.buyer.dto.BuyerRegistrationRequest;
import com.example.buyer.dto.BuyerRegistrationResponse;
import com.example.buyer.dto.MatchingConditionRequest;
import com.example.buyer.model.enums.Speciality;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BuyerService {
    Long createBuyer(BuyerRegistrationRequest buyerRegistrationRequest);
    List<BuyerRegistrationResponse> getAllBuyers();
    Page<BuyerRegistrationResponse> getAllBuyersPaginated(Integer page, Integer size, String sortBy, String sortDir);
}
