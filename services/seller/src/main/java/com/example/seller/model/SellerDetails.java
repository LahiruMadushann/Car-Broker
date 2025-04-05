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
@Table(name = "seller_details")
public class SellerDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_id")
    private Long sellerId;

    @Column(name = "seller_phone_number")
    private String sellerPhoneNumber;

    @Column(name = "seller_name")
    private String sellerName;

    @Column(name = "seller_email")
    private String sellerEmail;

    @Column(name = "seller_post_number")
    private String sellerPostNumber;

    @Column(name = "seller_district")
    private String sellerDistrict;

    @Column(name = "seller_city")
    private String sellerCity;

    @Column(name = "seller_address")
    private String sellerAddress;

    @OneToOne(mappedBy = "sellerDetails")
    private SellerCarDetails sellerCarDetails;

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "last_modified_date", insertable = false)
    private LocalDateTime lastModifiedDate;
}
