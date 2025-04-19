package com.example.buyer.dto;

import com.example.buyer.model.enums.Branch;
import com.example.buyer.model.enums.Speciality;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record BuyerRegistrationResponse(
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
