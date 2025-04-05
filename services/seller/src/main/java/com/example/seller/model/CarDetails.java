package com.example.seller.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "car_details")
public class CarDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long carId;

    @Column(name = "car_type")
    private String carType;

    @Column(name = "car_maker")
    private String carMaker;

    @Column(name = "car_model_year")
    private String carModelYear;

    @Column(name = "car_traveled_distance")
    private String carTraveledDistance;

    @Column(name = "inspect_remain")
    private String inspectRemain;

    @Column(name = "car_state")
    private String carState;

    @Column(name = "runnable")
    private String runnable;

    @Column(name = "wheel_drive")
    private String wheelDrive;

    @Column(name = "fuel")
    private String fuel;

    @Column(name = "shift")
    private String shift;

    @Column(name = "accident")
    private String accident;

    @Column(name = "displacement")
    private String displacement;

    @Column(name = "body_color")
    private String bodyColor;

    @Column(name = "loan")
    private String loan;

    @Column(name = "desire_date")
    private String desireDate;

    @Column(name = "grade")
    private String grade;

    @Column(name = "exterior")
    private String exterior;

    @Column(name = "scratch")
    private String scratch;

    @Column(name = "dent")
    private String dent;

    @Column(name = "peel")
    private String peel;

    @Column(name = "rust")
    private String rust;

    @Column(name = "interior")
    private String interior;

    @Column(name = "dirt")
    private String dirt;

    @Column(name = "tear")
    private String tear;

    @Column(name = "air_conditioning")
    private String airConditioning;

    @Column(name = "smoke")
    private String smoke;

    @Column(name = "navigation")
    private String navigation;

    @Column(name = "auto_slide")
    private String autoSlide;

    @Column(name = "leather_sheet")
    private String leatherSheet;

    @Column(name = "handle_type")
    private String handleType;

    @Column(name = "back_monitor")
    private String backMonitor;

    @Column(name = "sunroof")
    private String sunroof;

    @Column(name = "wheel")
    private String wheel;

    @OneToOne(mappedBy = "carDetails")
    private SellerCarDetails sellerCarDetails;
}
