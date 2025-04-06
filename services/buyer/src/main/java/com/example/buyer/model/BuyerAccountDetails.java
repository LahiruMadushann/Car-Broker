package com.example.buyer.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "buyer_account_details")
public class BuyerAccountDetails extends BaseEntity {
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

}
