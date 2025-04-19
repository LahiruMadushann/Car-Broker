package com.example.buyer.dto;

import com.example.buyer.model.enums.Branch;
import com.example.buyer.model.enums.Speciality;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record BuyerRegistrationRequest(
        Long buyerId,

        @NotNull(message = "Buyer name is required")
        String buyerName,

        @NotNull(message = "Buyer phone number is required")
        String buyerPhoneNumber,

        @NotNull(message = "Introduction fee is required")
        Double introductionFee,

        @NotNull(message = "Referral fee is required")
        Double referralFee,

        @NotNull(message = "Postal Code is required")
        String postalCode,

        @NotNull(message = "District is required")
        String district,

        @NotNull(message = "City is required")
        String city,

        @NotNull(message = "Buyer email is required")
        @Email(message = "Buyer email is not a valid email address")
        String email,

        @NotNull(message = "Branch is required")
        Branch branch,

        @NotNull(message = "Speciality is required")
        Speciality speciality
) {
}
