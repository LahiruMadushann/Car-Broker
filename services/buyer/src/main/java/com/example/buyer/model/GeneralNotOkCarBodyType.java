package com.example.buyer.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "general_not_ok_car_body_type")
public class GeneralNotOkCarBodyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "general_not_ok_car_body_type_id")
    private Long generalNotOkCarBodyTypeId;

    @Column(name = "general_not_ok_car_body_type")
    private String generalNotOkCarBodyType;

    @ManyToOne
    @JoinColumn(name = "general_condition_id")
    private GeneralCarHandlingConditions generalCarHandlingConditions;
}
