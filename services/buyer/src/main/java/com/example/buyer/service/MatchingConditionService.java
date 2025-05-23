package com.example.buyer.service;

import com.example.buyer.dto.MatchingConditionRequest;
import com.example.buyer.dto.MatchingConditionResponse;
import com.example.buyer.model.enums.Speciality;

import java.util.List;

public interface MatchingConditionService {
    Long createMatchingConditions(Long buyerId, Speciality speciality, MatchingConditionRequest matchingConditionRequest);
    List<MatchingConditionResponse> getAllMatchingConditions();


}
