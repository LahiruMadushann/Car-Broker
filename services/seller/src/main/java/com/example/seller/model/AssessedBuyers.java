package com.example.seller.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "assessed_buyers")
public class AssessedBuyers {

    @EmbeddedId
    private EmbeddedAppraisalId embeddedAppraisalId;

    @Column(name = "assessed_datetime")
    private LocalDateTime assessedDateTime;

    @Column(name = "email_sent_time")
    private LocalDateTime emailSentTime;

    @Column(name = "is_rejected_by_shop")
    private boolean reject;

    @Column(name = "assessed_ex")
    private String ex;

    @Column(name = "assessed_status")
    private String assessedStatus;

    @Column(name = "approval_date")
    private LocalDateTime approvalDate;

    @Column(name = "rejection_approval_date")
    private String rejectionApprovalDate;

    @LastModifiedDate
    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "appraisal_id", insertable = false, updatable = false)
    private SellerCarDetails sellerCarDetails;
}
