package com.example.seller.serviceImpl;

import com.example.seller.dto.EmailRequest;
import com.example.seller.dto.EmailResponse;
import com.example.seller.service.client.NotificationServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationHelper {

    private final NotificationServiceClient notificationClient;

    public void sendAppraisalSuccessNotification(String customerEmail, Long appraisalId, List<String> matchedShops) {
        try {
            String subject = "Appraisal Request Processed Successfully";
            String content = String.format("""
                <html>
                <body>
                    <h2>Appraisal Request Processed Successfully</h2>
                    <p>Dear Customer,</p>
                    <p>Your appraisal request has been processed successfully.</p>
                    <p><strong>Appraisal ID:</strong> %s</p>
                    <p><strong>Matched Shops:</strong> %s</p>
                    <p>Thank you for using our service.</p>
                    <br>
                    <p>Best regards,<br>Your Car Appraisal Team</p>
                </body>
                </html>
                """, appraisalId, String.join(", ", matchedShops));

            EmailRequest request = new EmailRequest(
                    List.of(customerEmail),
                    null,
                    null,
                    subject,
                    content,
                    true,
                    null,
                    null
            );

            EmailResponse response = notificationClient.sendEmail(request);
            log.info("Appraisal success notification sent: {}", response.message());
        } catch (Exception e) {
            log.error("Failed to send appraisal success notification: {}", e.getMessage());
        }
    }

    public void sendAppraisalFailureNotification(String customerEmail, String errorMessage) {
        try {
            String subject = "Appraisal Request Failed";
            String content = String.format("""
                <html>
                <body>
                    <h2>Appraisal Request Failed</h2>
                    <p>Dear Customer,</p>
                    <p>Unfortunately, your appraisal request could not be processed.</p>
                    <p><strong>Reason:</strong> %s</p>
                    <p>Please contact our support team for assistance.</p>
                    <br>
                    <p>Best regards,<br>Your Car Appraisal Team</p>
                </body>
                </html>
                """, errorMessage);

            EmailRequest request = new EmailRequest(
                    List.of(customerEmail),
                    null,
                    null,
                    subject,
                    content,
                    true,
                    null,
                    null
            );

            EmailResponse response = notificationClient.sendEmail(request);
            log.info("Appraisal failure notification sent: {}", response.message());
        } catch (Exception e) {
            log.error("Failed to send appraisal failure notification: {}", e.getMessage());
        }
    }

    public void sendSimpleNotification(String email, String subject, String message) {
        try {
            EmailResponse response = notificationClient.sendSimpleEmail(email, subject, message);
            log.info("Simple notification sent: {}", response.message());
        } catch (Exception e) {
            log.error("Failed to send simple notification: {}", e.getMessage());
        }
    }
}