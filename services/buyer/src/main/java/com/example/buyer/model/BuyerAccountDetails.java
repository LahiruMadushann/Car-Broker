package com.example.buyer.model;

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
@Table(name = "buyer_account_details")
public class BuyerAccountDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buyer_account_id")
    private Long buyerAccountId;

    @Column(name = "buyer_user_name")
    private String buyerUserName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "last_modified_date", insertable = false)
    private LocalDateTime lastModifiedDate;
}
