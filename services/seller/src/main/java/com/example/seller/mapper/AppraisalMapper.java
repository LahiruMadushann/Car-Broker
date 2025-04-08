package com.example.seller.mapper;

import com.example.seller.dto.SellerCarDetailsRequest;
import com.example.seller.model.CarDetails;
import com.example.seller.model.SellerCarDetails;
import com.example.seller.model.SellerDetails;
import org.springframework.stereotype.Service;

@Service
public class AppraisalMapper {
    public SellerCarDetails toSeller(SellerCarDetailsRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("sellerCarDetailsRequest cannot be null");
        }

        SellerDetails sellerDetails = SellerDetails.builder()
                .sellerPhoneNumber(request.sellerPhoneNumber())
                .sellerName(request.sellerName())
                .sellerEmail(request.sellerEmail())
                .sellerPostNumber(request.sellerPostNumber())
                .sellerDistrict(request.sellerDistrict())
                .sellerCity(request.sellerCity())
                .sellerAddress(request.sellerAddress())
                .build();

        CarDetails carDetails = CarDetails.builder()
                .carType(request.carType())
                .carMaker(request.carMaker())
                .carModelYear(request.carModelYear())
                .carTraveledDistance(request.carTraveledDistance())
                .inspectRemain(request.inspectRemain())
                .carState(request.carState())
                .runnable(request.runnable())
                .wheelDrive(request.wheelDrive())
                .fuel(request.fuel())
                .shift(request.shift())
                .accident(request.accident())
                .displacement(request.displacement())
                .bodyColor(request.bodyColor())
                .loan(request.loan())
                .desireDate(request.desireDate())
                .grade(request.grade())
                .exterior(request.exterior())
                .scratch(request.scratch())
                .dent(request.dent())
                .peel(request.peel())
                .rust(request.rust())
                .interior(request.interior())
                .dirt(request.dirt())
                .tear(request.tear())
                .airConditioning(request.airConditioning())
                .smoke(request.smoke())
                .navigation(request.navigation())
                .autoSlide(request.autoSlide())
                .leatherSheet(request.leatherSheet())
                .handleType(request.handleType())
                .backMonitor(request.backMonitor())
                .sunroof(request.sunroof())
                .wheel(request.wheel())
                .build();

        return SellerCarDetails.builder()
                .appraisalId(request.appraisalId())
                .status(request.status())
                .ip(request.ip())
                .sellerDetails(sellerDetails)
                .carDetails(carDetails)
                .desireDateToSell(request.desireDateToSell())
                .photoFrontView(request.photoFrontView())
                .photoBackView(request.photoBackView())
                .inspectionCertPhoto(request.inspectionCertPhoto())
                .build();
    }
}
