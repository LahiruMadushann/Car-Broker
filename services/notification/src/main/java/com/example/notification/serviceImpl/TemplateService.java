package com.example.notification.serviceImpl;

import com.example.notification.model.enums.EmailTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.example.notification.model.enums.EmailTemplate.APPRAISAL_SUCCESS;

@Service
public class TemplateService {

    public String processTemplate(EmailTemplate template, Map<String, Object> variables) {
        return switch (template) {
            case APPRAISAL_SUCCESS -> processAppraisalSuccessTemplate(variables);
            case APPRAISAL_FAILURE -> processAppraisalFailureTemplate(variables);
            case BUYER_MATCH_FOUND -> processBuyerMatchFoundTemplate(variables);
            case GENERAL_NOTIFICATION -> processGeneralNotificationTemplate(variables);
        };
    }

    private String processAppraisalSuccessTemplate(Map<String, Object> variables) {
        return """
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
            """.formatted(
                variables.getOrDefault("appraisalId", "N/A"),
                variables.getOrDefault("matchedShops", "None")
        );
    }

    private String processAppraisalFailureTemplate(Map<String, Object> variables) {
        return """
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
            """.formatted(
                variables.getOrDefault("errorMessage", "Unknown error")
        );
    }

    private String processBuyerMatchFoundTemplate(Map<String, Object> variables) {
        return """
            <html>
            <body>
                <h2>Matching Buyers Found</h2>
                <p>Dear Customer,</p>
                <p>We found matching buyers for your vehicle.</p>
                <p><strong>Number of Matches:</strong> %s</p>
                <p>Our team will contact you soon with more details.</p>
                <br>
                <p>Best regards,<br>Your Car Appraisal Team</p>
            </body>
            </html>
            """.formatted(
                variables.getOrDefault("matchCount", "0")
        );
    }

    private String processGeneralNotificationTemplate(Map<String, Object> variables) {
        return """
            <html>
            <body>
                <h2>Notification</h2>
                <p>Dear Customer,</p>
                <p>%s</p>
                <br>
                <p>Best regards,<br>Your Service Team</p>
            </body>
            </html>
            """.formatted(
                variables.getOrDefault("message", "You have a new notification.")
        );
    }
}
