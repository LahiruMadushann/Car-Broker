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
@Table(name = "seller_account_details")
public class SellerAccountDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_account_id")
    private Integer sellerAccountId;

    @Column(name = "seller_account_user_name", nullable = false)
    private String sellerAccountUserName;

    @Column(name = "seller_account_email",nullable = false)
    private String sellerAccountEmail;

    @Column(name = "seller_account_password", nullable = false)
    private String sellerAccountPassword;

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "last_modified_date", insertable = false)
    private LocalDateTime lastModifiedDate;

    @OneToOne(mappedBy = "sellerAccountDetails")
    private SellerCarDetails sellerCarDetails;
}
