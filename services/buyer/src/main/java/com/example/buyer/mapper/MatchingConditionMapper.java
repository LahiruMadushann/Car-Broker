package com.example.buyer.mapper;

import com.example.buyer.dto.BuyerRegistrationRequest;
import com.example.buyer.dto.CarHandlingConditionRequest;
import com.example.buyer.dto.MatchingConditionRequest;
import com.example.buyer.model.*;
import com.example.buyer.model.enums.Speciality;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchingConditionMapper {

    public BuyerDetails toSpecialMatchingCondition(MatchingConditionRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Matching condition request cannot be null");
        }

        List<BuyerEmails> emails = request.buyerEmails().stream()
                .map(email -> BuyerEmails.builder()
                        .buyerEmail(email)
                        .build())
                .collect(Collectors.toList());

        List<BuyerArea> areas = request.buyerArea().stream()
                .map(area -> BuyerArea.builder()
                        .postalCode(area.postalCode())
                        .district(area.district())
                        .city(area.city())
                        .build())
                .collect(Collectors.toList());

        SpecialCarHandlingConditions specialConditions = createSpecialCarHandlingConditions(request.carHandlingConditions());

        List<SpecialCarType> specialCarTypes = request.carType().stream()
                .map(type -> SpecialCarType.builder()
                        .specialCarType(type.carType())
                        .specialCarMaker(type.carMaker())
                        .build())
                .collect(Collectors.toList());

        List<SpecialCarMaker> specialCarMakers = request.carMaker().stream()
                .map(maker -> SpecialCarMaker.builder()
                        .specialCarMaker(maker)
                        .build())
                .collect(Collectors.toList());

        List<SpecialCarBodyType> specialCarBodyTypes = request.bodyType().stream()
                .map(body -> SpecialCarBodyType.builder()
                        .specialCarBodyType(body)
                        .build())
                .collect(Collectors.toList());

        specialConditions.setSpecialCarType(specialCarTypes);
        specialConditions.setSpecialCarMaker(specialCarMakers);
        specialConditions.setSpecialCarBodyType(specialCarBodyTypes);

        specialCarTypes.forEach(type -> type.setSpecialCarHandlingConditions(specialConditions));
        specialCarMakers.forEach(maker -> maker.setSpecialCarHandlingConditions(specialConditions));
        specialCarBodyTypes.forEach(body -> body.setSpecialCarHandlingConditions(specialConditions));

        BuyerDetails buyerDetails = BuyerDetails.builder()
                .buyerEmails(emails)
                .buyerArea(areas)
                .specialCarHandlingConditions(specialConditions)
                .build();

        emails.forEach(email -> email.setBuyerDetails(buyerDetails));
        areas.forEach(area -> area.setBuyerDetails(buyerDetails));
        specialConditions.setBuyerDetails(buyerDetails);

        return buyerDetails;
    }

    public BuyerDetails toGeneralMatchingCondition(MatchingConditionRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Matching condition request cannot be null");
        }

        List<BuyerEmails> emails = request.buyerEmails().stream()
                .map(email -> BuyerEmails.builder()
                        .buyerEmail(email)
                        .build())
                .collect(Collectors.toList());

        List<BuyerArea> areas = request.buyerArea().stream()
                .map(area -> BuyerArea.builder()
                        .postalCode(area.postalCode())
                        .district(area.district())
                        .city(area.city())
                        .build())
                .collect(Collectors.toList());

        GeneralCarHandlingConditions generalConditions = createGeneralCarHandlingConditions(request.carHandlingConditions());

        List<GeneralOkCarType> generalOkCarTypes = request.carType().stream()
                .map(type -> GeneralOkCarType.builder()
                        .generalOkCarType(type.carType())
                        .generalOkCarMaker(type.carMaker())
                        .build())
                .collect(Collectors.toList());

        List<GeneralOkCarMaker> generalOkCarMakers = request.carMaker().stream()
                .map(maker -> GeneralOkCarMaker.builder()
                        .generalOkCarMaker(maker)
                        .build())
                .collect(Collectors.toList());

        List<GeneralOkCarBodyType> generalOkCarBodyTypes = request.bodyType().stream()
                .map(body -> GeneralOkCarBodyType.builder()
                        .generalOkCarBodyType(body)
                        .build())
                .collect(Collectors.toList());

        List<GeneralNotOkCarType> generalNotOkCarTypes = request.ngCarType().stream()
                .map(type -> GeneralNotOkCarType.builder()
                        .generalNotOkCarType(type.ngCarType())
                        .generalNotOkCarMaker(type.ngCarMaker())
                        .build())
                .collect(Collectors.toList());

        List<GeneralNotOkCarMaker> generalNotOkCarMakers = request.ngCarMaker().stream()
                .map(maker -> GeneralNotOkCarMaker.builder()
                        .generalNotOkCarMaker(maker)
                        .build())
                .collect(Collectors.toList());

        List<GeneralNotOkCarBodyType> generalNotOkCarBodyTypes = request.ngBodyType().stream()
                .map(body -> GeneralNotOkCarBodyType.builder()
                        .generalNotOkCarBodyType(body)
                        .build())
                .collect(Collectors.toList());

        generalConditions.setGeneralOkCarTypes(generalOkCarTypes);
        generalConditions.setGeneralOkCarMaker(generalOkCarMakers);
        generalConditions.setGeneralOkCarBodyType(generalOkCarBodyTypes);
        generalConditions.setGeneralNotOkCarTypes(generalNotOkCarTypes);
        generalConditions.setGeneralNotOkCarMaker(generalNotOkCarMakers);
        generalConditions.setGeneralNotOkCarBodyType(generalNotOkCarBodyTypes);

        generalOkCarTypes.forEach(type -> type.setGeneralCarHandlingConditions(generalConditions));
        generalOkCarMakers.forEach(maker -> maker.setGeneralCarHandlingConditions(generalConditions));
        generalOkCarBodyTypes.forEach(body -> body.setGeneralCarHandlingConditions(generalConditions));
        generalNotOkCarTypes.forEach(type -> type.setGeneralCarHandlingConditions(generalConditions));
        generalNotOkCarMakers.forEach(maker -> maker.setGeneralCarHandlingConditions(generalConditions));
        generalNotOkCarBodyTypes.forEach(body -> body.setGeneralCarHandlingConditions(generalConditions));

        BuyerDetails buyerDetails = BuyerDetails.builder()
                .buyerEmails(emails)
                .buyerArea(areas)
                .generalCarHandlingConditions(generalConditions)
                .build();

        emails.forEach(email -> email.setBuyerDetails(buyerDetails));
        areas.forEach(area -> area.setBuyerDetails(buyerDetails));
        generalConditions.setBuyerDetails(buyerDetails);

        return buyerDetails;
    }


    private SpecialCarHandlingConditions createSpecialCarHandlingConditions(CarHandlingConditionRequest conditions) {
        return SpecialCarHandlingConditions.builder()
                .carOrigin(conditions.carOrigin())
                .notMove(conditions.notMove())
                .shopCeilMatchCount(conditions.shopCeilMatchCount())
                .carYearOkFrom(conditions.carYearOkFrom())
                .carYearOkTo(conditions.carYearOkTo())
                .carYearNgFrom(conditions.carYearNgFrom())
                .carYearNgTo(conditions.carYearNgTo())
                .carTravelDistanceOkFrom(conditions.carTravelDistanceOkFrom())
                .carTravelDistanceOkTo(conditions.carTravelDistanceOkTo())
                .carTravelDistanceNgFrom(conditions.carTravelDistanceNgFrom())
                .carTravelDistanceNgTo(conditions.carTravelDistanceNgTo())
                .exclusivity(conditions.exclusivity())
                .build();
    }

    private GeneralCarHandlingConditions createGeneralCarHandlingConditions(CarHandlingConditionRequest conditions) {
        return GeneralCarHandlingConditions.builder()
                .carOrigin(conditions.carOrigin())
                .notMove(conditions.notMove())
                .shopCeilMatchCount(conditions.shopCeilMatchCount())
                .carYearOkFrom(conditions.carYearOkFrom())
                .carYearOkTo(conditions.carYearOkTo())
                .carYearNgFrom(conditions.carYearNgFrom())
                .carYearNgTo(conditions.carYearNgTo())
                .carTravelDistanceOkFrom(conditions.carTravelDistanceOkFrom())
                .carTravelDistanceOkTo(conditions.carTravelDistanceOkTo())
                .carTravelDistanceNgFrom(conditions.carTravelDistanceNgFrom())
                .carTravelDistanceNgTo(conditions.carTravelDistanceNgTo())
                .exclusivity(conditions.exclusivity())
                .build();
    }
}