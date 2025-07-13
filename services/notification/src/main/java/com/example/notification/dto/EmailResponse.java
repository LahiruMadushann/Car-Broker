package com.example.notification.dto;

public record EmailResponse(
        boolean success,
        String message,
        String emailId
) {
}
