package com.example.seller.dto;

public record BuyerAreaResponse(
        String postalCode,
        String district,
        String city
) {
}
