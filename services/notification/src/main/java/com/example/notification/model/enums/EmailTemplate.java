package com.example.notification.model.enums;

public enum EmailTemplate {
    APPRAISAL_SUCCESS("appraisal-success", "Appraisal Request Processed Successfully"),
    APPRAISAL_FAILURE("appraisal-failure", "Appraisal Request Failed"),
    BUYER_MATCH_FOUND("buyer-match-found", "Matching Buyers Found"),
    GENERAL_NOTIFICATION("general-notification", "Notification");

    private final String templateName;
    private final String defaultSubject;

    EmailTemplate(String templateName, String defaultSubject) {
        this.templateName = templateName;
        this.defaultSubject = defaultSubject;
    }

    public String getTemplateName() {
        return templateName;
    }

    public String getDefaultSubject() {
        return defaultSubject;
    }
}
