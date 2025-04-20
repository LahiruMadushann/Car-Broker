package com.example.seller.dto;

import com.example.seller.model.enums.Branch;
import com.example.seller.model.enums.Speciality;

import java.util.List;

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
