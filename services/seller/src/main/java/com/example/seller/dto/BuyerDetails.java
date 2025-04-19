package com.example.seller.dto;

import com.example.seller.model.enums.Branch;
import com.example.seller.model.enums.Speciality;

public record BuyerDetails(
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
        Speciality speciality
) {
}
