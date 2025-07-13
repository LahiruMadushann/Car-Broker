package com.example.notification.service;

import com.example.notification.dto.EmailRequest;
import com.example.notification.dto.EmailResponse;
import com.example.notification.model.enums.EmailTemplate;

import java.util.List;
import java.util.Map;

public interface EmailService {
    EmailResponse sendEmail(EmailRequest request);
    EmailResponse sendTemplatedEmail(List<String> recipients, EmailTemplate template, Map<String, Object> variables);
    EmailResponse sendSimpleEmail(String to, String subject, String content);
    EmailResponse sendHtmlEmail(String to, String subject, String htmlContent);
}
