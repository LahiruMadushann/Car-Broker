package com.example.buyer.dto;

public record BuyerAreaRequest(
        String postalCode,
        String district,
        String city
) {
}
