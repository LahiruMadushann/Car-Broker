package com.example.buyer.model;

import com.example.buyer.model.enums.Branch;
import com.example.buyer.model.enums.Speciality;
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
@Table(name = "buyer_details")
public class BuyerDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buyer_id")
    private Long buyerId;

    @Column(name = "buyer_name")
    private String buyerName;

    @Column(name = "buyer_phone_number")
    private String buyerPhoneNumber;

    @Column(name = "introduction_fee")
    private String introductionFee;

    @Column(name = "referral_fee")
    private String referralFee;

    @Column(name = "branch")
    @Enumerated(EnumType.STRING)
    private Branch branch;

    @Column(name = "speciality")
    @Enumerated(EnumType.STRING)
    private Speciality speciality;

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "last_modified_date", insertable = false)
    private LocalDateTime lastModifiedDate;

    @OneToMany(mappedBy = "buyerDetails", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BuyerEmails> buyerEmails;

    @OneToMany(mappedBy = "buyerDetails", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BuyerArea> buyerArea;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "special_condition_id", referencedColumnName = "special_condition_id")
    private SpecialCarHandlingConditions specialCarHandlingConditions;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "general_condition_id", referencedColumnName = "general_condition_id")
    private GeneralCarHandlingConditions generalCarHandlingConditions;
}
