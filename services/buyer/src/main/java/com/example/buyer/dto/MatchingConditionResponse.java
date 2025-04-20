package com.example.buyer.dto;

import com.example.buyer.model.enums.Branch;
import com.example.buyer.model.enums.Speciality;
import lombok.Builder;

import java.util.List;

@Builder
public record MatchingConditionResponse(
        Long buyerId,
        String buyerName,
        String buyerPhoneNumber,
        Double introductionFee,
        Double referralFee,
        String postalCode,
        String district,
        String city,
        String email,
        Branch branch,
        Speciality speciality,
        List<String> buyerEmails,
        List<BuyerAreaResponse> buyerAreas,
        CarHandlingConditionsResponse specialCarHandlingConditions,
        CarHandlingConditionsResponse generalCarHandlingConditions
) {
}
