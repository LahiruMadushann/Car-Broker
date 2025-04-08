package com.example.seller.dto;

import com.example.seller.model.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

public record SellerCarDetailsRequest(
        Long appraisalId,

        @NotNull(message = "Seller status is required")
        String status,

        @NotNull(message = "Seller IP is required")
        String ip,

        @NotNull(message = "Seller phone number is required")
        String sellerPhoneNumber,

        @NotNull(message = "Seller name is required")
        String sellerName,

        @NotNull(message = "Seller email is required")
        @Email(message = "Seller email is not a valid email address")
        String sellerEmail,

        @NotNull(message = "Seller post number is required")
        String sellerPostNumber,

        @NotNull(message = "Seller district is required")
        String sellerDistrict,

        @NotNull(message = "Seller city is required")
        String sellerCity,

        String sellerAddress,
        String carType,
        String carMaker,
        String carModelYear,
        String carTraveledDistance,
        String inspectRemain,
        String carState,
        String runnable,
        String wheelDrive,
        String fuel,
        String shift,
        String accident,
        String displacement,
        String bodyColor,
        String loan,
        String desireDate,
        String grade,
        String exterior,
        String scratch,
        String dent,
        String peel,
        String rust,
        String interior,
        String dirt,
        String tear,
        String airConditioning,
        String smoke,
        String navigation,
        String autoSlide,
        String leatherSheet,
        String handleType,
        String backMonitor,
        String sunroof,
        String wheel,
        String desireDateToSell,
        String photoFrontView,
        String photoBackView,
        String inspectionCertPhoto
) {
}
