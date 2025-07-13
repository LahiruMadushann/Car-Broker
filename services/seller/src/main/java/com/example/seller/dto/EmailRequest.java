package com.example.seller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.Map;

public record EmailRequest(
        @NotEmpty(message = "At least one recipient is required")
        List<@Email(message = "Invalid email format") String> to,

        List<@Email(message = "Invalid email format") String> cc,

        List<@Email(message = "Invalid email format") String> bcc,

        @NotBlank(message = "Subject is required")
        String subject,

        @NotBlank(message = "Content is required")
        String content,

        boolean isHtml,

        Map<String, Object> templateVariables,

        String templateName
) {
}
