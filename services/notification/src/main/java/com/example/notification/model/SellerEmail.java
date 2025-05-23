package com.example.notification.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Entity
@DiscriminatorValue("S")
public class SellerEmail extends Email {
    @Column(name = "seller_id")
    private Long sellerId;

    @Column(name = "seller_email")
    private String sellerEmail;
}
