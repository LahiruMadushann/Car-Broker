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
@Table(name = "buyer_area")
public class BuyerArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_id")
    private Long areaId;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "district")
    private String district;

    @Column(name = "city")
    private String city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id")
    private BuyerDetails buyerDetails;
}
