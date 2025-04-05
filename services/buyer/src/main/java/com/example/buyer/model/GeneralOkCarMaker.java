package com.example.buyer.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "general_ok_car_maker")
public class GeneralOkCarMaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "general_ok_car_maker_id")
    private Long generalOkCarMakerId;

    @Column(name = "general_ok_car_maker")
    private String generalOkCarMaker;

    @ManyToOne
    @JoinColumn(name = "general_condition_id")
    private GeneralCarHandlingConditions generalCarHandlingConditions;
}
