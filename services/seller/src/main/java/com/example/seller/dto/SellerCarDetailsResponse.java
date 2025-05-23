package com.example.seller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record SellerCarDetailsResponse(
        Long appraisalId,
        String status,
        String ip,
        String sellerPhoneNumber,
        String sellerName,
        String sellerEmail,
        String sellerPostNumber,
        String sellerDistrict,
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
        String inspectionCertPhoto,
        List<AssessedBuyersResponse> assessedBuyers
) {
}
