package com.example.notification.serviceImpl;

import com.example.notification.dto.EmailRequest;
import com.example.notification.dto.EmailResponse;
import com.example.notification.model.enums.EmailTemplate;
import com.example.notification.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    private final TemplateService templateService;

    @Override
    public EmailResponse sendEmail(EmailRequest request) {
        try {
            String emailId = UUID.randomUUID().toString();

            if (request.isHtml() || request.templateName() != null) {
                return sendMimeEmail(request, emailId);
            } else {
                return sendSimpleTextEmail(request, emailId);
            }
        } catch (Exception e) {
            log.error("Failed to send email: {}", e.getMessage(), e);
            return new EmailResponse(false, "Failed to send email: " + e.getMessage(), null);
        }
    }

    @Override
    public EmailResponse sendTemplatedEmail(List<String> recipients, EmailTemplate template, Map<String, Object> variables) {
        try {
            String processedContent = templateService.processTemplate(template, variables);

            EmailRequest request = new EmailRequest(
                    recipients,
                    null,
                    null,
                    template.getDefaultSubject(),
                    processedContent,
                    true,
                    variables,
                    template.getTemplateName()
            );

            return sendEmail(request);
        } catch (Exception e) {
            log.error("Failed to send templated email: {}", e.getMessage(), e);
            return new EmailResponse(false, "Failed to send templated email: " + e.getMessage(), null);
        }
    }

    @Override
    public EmailResponse sendSimpleEmail(String to, String subject, String content) {
        EmailRequest request = new EmailRequest(
                List.of(to),
                null,
                null,
                subject,
                content,
                false,
                null,
                null
        );
        return sendEmail(request);
    }

    @Override
    public EmailResponse sendHtmlEmail(String to, String subject, String htmlContent) {
        EmailRequest request = new EmailRequest(
                List.of(to),
                null,
                null,
                subject,
                htmlContent,
                true,
                null,
                null
        );
        return sendEmail(request);
    }

    private EmailResponse sendSimpleTextEmail(EmailRequest request, String emailId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.to().toArray(new String[0]));

        if (request.cc() != null && !request.cc().isEmpty()) {
            message.setCc(request.cc().toArray(new String[0]));
        }

        message.setSubject(request.subject());
        message.setText(request.content());
        message.setFrom("noreply@yourcompany.com");

        mailSender.send(message);
        log.info("Simple email sent successfully with ID: {}", emailId);
        return new EmailResponse(true, "Email sent successfully", emailId);
    }

    private EmailResponse sendMimeEmail(EmailRequest request, String emailId) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(request.to().toArray(new String[0]));

        if (request.cc() != null && !request.cc().isEmpty()) {
            helper.setCc(request.cc().toArray(new String[0]));
        }

        if (request.bcc() != null && !request.bcc().isEmpty()) {
            helper.setBcc(request.bcc().toArray(new String[0]));
        }

        helper.setSubject(request.subject());
        helper.setText(request.content(), request.isHtml());
        helper.setFrom("noreply@yourcompany.com");

        mailSender.send(message);
        log.info("MIME email sent successfully with ID: {}", emailId);
        return new EmailResponse(true, "Email sent successfully", emailId);
    }
}
