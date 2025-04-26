package com.example.seller.dto;

import java.time.LocalDateTime;

public record AssessedBuyersResponse(
        Long appraisalId,
        Integer buyerId,
        LocalDateTime assessedDateTime,
        LocalDateTime emailSentTime,
        boolean reject,
        String ex,
        String assessedStatus,
        LocalDateTime approvalDate,
        String rejectionApprovalDate,
        LocalDateTime updatedAt,
        LocalDateTime createdAt
) {
}
