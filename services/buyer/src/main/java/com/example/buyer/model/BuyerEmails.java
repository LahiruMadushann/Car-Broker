package com.example.buyer.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "buyer_emails")
public class BuyerEmails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email_id")
    private Long emailId;

    @Column(name = "buyer_email")
    private String buyerEmail;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private BuyerDetails buyerDetails;
}
