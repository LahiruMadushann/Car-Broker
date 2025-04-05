package com.example.buyer.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "special_car_maker")
public class SpecialCarMaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "special_car_maker_id")
    private Long specialCarMakerId;

    @Column(name = "special_car_maker")
    private String specialCarMaker;

    @ManyToOne
    @JoinColumn(name = "special_condition_id")
    private SpecialCarHandlingConditions specialCarHandlingConditions;
}
