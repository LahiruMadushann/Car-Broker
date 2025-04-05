package com.example.buyer.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "special_car_type")
public class SpecialCarType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "special_car_type_id")
    private Long specialCarTypeId;

    @Column(name = "special_car_type")
    private String specialCarType;

    @Column(name = "special_car_maker")
    private String specialCarMaker;

    @ManyToOne
    @JoinColumn(name = "special_condition_id")
    private SpecialCarHandlingConditions specialCarHandlingConditions;
}
