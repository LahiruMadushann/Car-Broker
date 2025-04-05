package com.example.buyer.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "general_ok_car_type")
public class GeneralOkCarType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "general_ok_car_type_id")
    private Long generalOkCarTypeId;

    @Column(name = "general_ok_car_type")
    private String generalOkCarType;

    @Column(name = "general_ok_car_maker")
    private String generalOkCarMaker;

    @ManyToOne
    @JoinColumn(name = "general_condition_id")
    private GeneralCarHandlingConditions generalCarHandlingConditions;
}
