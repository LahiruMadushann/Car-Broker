package com.example.notification.controller;

import com.example.notification.dto.EmailRequest;
import com.example.notification.dto.EmailResponse;
import com.example.notification.service.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send-email")
    public ResponseEntity<EmailResponse> sendEmail(@Valid @RequestBody EmailRequest request) {
        EmailResponse response = emailService.sendEmail(request);
        return response.success() ?
                ResponseEntity.ok(response) :
                ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/send-simple-email")
    public ResponseEntity<EmailResponse> sendSimpleEmail(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String content) {
        EmailResponse response = emailService.sendSimpleEmail(to, subject, content);
        return response.success() ?
                ResponseEntity.ok(response) :
                ResponseEntity.badRequest().body(response);
    }
}
