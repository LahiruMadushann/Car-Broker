package com.example.seller.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "seller_car_details")
public class SellerCarDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appraisal_id")
    private Long appraisalId;

    @Column(name = "status")
    private String status;

    @Column(name = "ip")
    private String ip;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", referencedColumnName = "seller_id")
    private SellerDetails sellerDetails;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", referencedColumnName = "car_id")
    private CarDetails carDetails;

    @OneToMany(mappedBy = "sellerCarDetails", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AssessedBuyers> assessedBuyers;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_account_id", referencedColumnName = "seller_account_id")
    private SellerAccountDetails sellerAccountDetails;

    @Column(name = "desire_date_to_sell")
    private String desireDateToSell;

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "last_modified_date", insertable = false)
    private LocalDateTime lastModifiedDate;

    @Column(name = "photo_front_view")
    private String photoFrontView;

    @Column(name = "photo_back_view")
    private String photoBackView;

    @Column(name = "inspection_cert_photo")
    private String inspectionCertPhoto;

}
