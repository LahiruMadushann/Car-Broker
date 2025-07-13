package com.example.seller.service.client;

import com.example.seller.dto.EmailRequest;
import com.example.seller.dto.EmailResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "notification-service", url = "http://localhost:8087")
public interface NotificationServiceClient {

    @PostMapping("/api/v1/notifications/send-email")
    EmailResponse sendEmail(@RequestBody EmailRequest request);

    @PostMapping("/api/v1/notifications/send-simple-email")
    EmailResponse sendSimpleEmail(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String content
    );
}
