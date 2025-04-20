package com.example.seller.service;

import com.example.seller.dto.MatchingConditionResponse;
import com.example.seller.model.SellerCarDetails;

import java.util.List;

public interface MatchingConditionService {
    List<Long> automaticMatch(List<MatchingConditionResponse> matchingConditionResponse, SellerCarDetails sellerCarDetails);
}
