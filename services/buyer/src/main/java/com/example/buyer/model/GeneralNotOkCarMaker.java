package com.example.buyer.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "general_not_ok_car_maker")
public class GeneralNotOkCarMaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "general_not_ok_car_maker_id")
    private Long generalNotOkCarMakerId;

    @Column(name = "general_not_ok_car_maker")
    private String generalNotOkCarMaker;

    @ManyToOne
    @JoinColumn(name = "general_condition_id")
    private GeneralCarHandlingConditions generalCarHandlingConditions;
}
