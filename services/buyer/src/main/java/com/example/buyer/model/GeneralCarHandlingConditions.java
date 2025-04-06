package com.example.buyer.model;

import com.example.buyer.model.enums.CarOrigin;
import com.example.buyer.model.enums.Exclusivity;
import com.example.buyer.model.enums.NotMove;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "general_car_handling_conditions")
public class GeneralCarHandlingConditions extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "general_condition_id")
    private Long generalConditionId;

    @Column(name = "car_origin")
    @Enumerated(EnumType.STRING)
    private CarOrigin carOrigin;

    @Column(name = "not_move")
    @Enumerated(EnumType.STRING)
    private NotMove notMove;

    @Column(name = "shop_ceil_match_count")
    private Integer shopCeilMatchCount;

    @Column(name = "car_year_ok_from")
    private Long carYearOkFrom;

    @Column(name = "car_year_ok_to")
    private Long carYearOkTo;

    @Column(name = "car_year_ng_from")
    private Long carYearNgFrom;

    @Column(name = "car_year_ng_to")
    private Long carYearNgTo;

    @Column(name = "car_travel_distance_ok_from")
    private Long carTravelDistanceOkFrom;

    @Column(name = "car_travel_distance_ok_to")
    private Long carTravelDistanceOkTo;

    @Column(name = "car_travel_distance_ng_from")
    private Long carTravelDistanceNgFrom;

    @Column(name = "car_travel_distance_ng_to")
    private Long carTravelDistanceNgTo;

    @Column(name = "exclusivity")
    private Exclusivity exclusivity;

    @OneToMany(mappedBy = "generalCarHandlingConditions", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GeneralOkCarType> generalOkCarTypes;

    @OneToMany(mappedBy = "generalCarHandlingConditions", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GeneralOkCarMaker> generalOkCarMaker;

    @OneToMany(mappedBy = "generalCarHandlingConditions", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GeneralOkCarBodyType> generalOkCarBodyType;

    @OneToMany(mappedBy = "generalCarHandlingConditions", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GeneralNotOkCarType> generalNotOkCarTypes;

    @OneToMany(mappedBy = "generalCarHandlingConditions", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GeneralNotOkCarMaker> generalNotOkCarMaker;

    @OneToMany(mappedBy = "generalCarHandlingConditions", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GeneralNotOkCarBodyType> generalNotOkCarBodyType;

    @OneToOne(mappedBy = "generalCarHandlingConditions")
    private BuyerDetails buyerDetails;
}
