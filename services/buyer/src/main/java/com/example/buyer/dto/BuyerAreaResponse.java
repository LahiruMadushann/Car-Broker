package com.example.buyer.dto;

public record BuyerAreaResponse(
        String postalCode,
        String district,
        String city
) {
}
