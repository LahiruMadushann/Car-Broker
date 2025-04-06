package com.example.notification.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Entity
@DiscriminatorValue("B")
public class BuyerEmail extends Email {
    @Column(name = "buyer_id")
    private Long buyerId;

    @Column(name = "buyer_email")
    private String buyerEmail;
}
