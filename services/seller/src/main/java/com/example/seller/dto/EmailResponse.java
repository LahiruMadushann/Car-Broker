package com.example.seller.dto;

public record EmailResponse(
        boolean success,
        String message,
        String emailId
) {
}
