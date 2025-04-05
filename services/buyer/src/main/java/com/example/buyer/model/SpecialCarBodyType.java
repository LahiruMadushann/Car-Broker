package com.example.buyer.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "special_car_body_type")
public class SpecialCarBodyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "special_car_body_type_id")
    private Long specialCarBodyTypeId;

    @Column(name = "special_car_body_type")
    private String specialCarBodyType;

    @ManyToOne
    @JoinColumn(name = "special_condition_id")
    private SpecialCarHandlingConditions specialCarHandlingConditions;
}
