package com.example.buyer.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "general_ok_car_body_type")
public class GeneralOkCarBodyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "general_ok_car_body_type_id")
    private Long generalOkCarBodyTypeId;

    @Column(name = "general_ok_car_body_type")
    private String generalOkCarBodyType;

    @ManyToOne
    @JoinColumn(name = "general_ok_condition_id")
    private GeneralCarHandlingConditions generalCarHandlingConditions;
}
