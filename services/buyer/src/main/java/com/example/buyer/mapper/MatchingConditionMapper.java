package com.example.buyer.mapper;

import com.example.buyer.dto.*;
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

    public MatchingConditionResponse fromMatchingCondition(BuyerDetails buyer){
        if (buyer == null) {
            throw new IllegalArgumentException("BuyerDetails cannot be null");
        }

        var account = buyer.getBuyerAccountDetails();
        String email = account != null ? account.getEmail() : null;

        List<String> buyerEmails = buyer.getBuyerEmails() != null ?
                buyer.getBuyerEmails().stream()
                        .map(BuyerEmails::getBuyerEmail)
                        .collect(Collectors.toList()) :
                List.of();

        List<BuyerAreaResponse> buyerAreas = buyer.getBuyerArea() != null ?
                buyer.getBuyerArea().stream()
                        .map(area -> new BuyerAreaResponse(
                                area.getPostalCode(),
                                area.getDistrict(),
                                area.getCity()
                        ))
                        .collect(Collectors.toList()) :
                List.of();

        CarHandlingConditionsResponse specialConditions = null;
        if (buyer.getSpecialCarHandlingConditions() != null) {
            var special = buyer.getSpecialCarHandlingConditions();
            specialConditions = mapToCarHandlingConditionsResponse(special);
        }

        CarHandlingConditionsResponse generalConditions = null;
        if (buyer.getGeneralCarHandlingConditions() != null) {
            var general = buyer.getGeneralCarHandlingConditions();
            generalConditions = mapToCarHandlingConditionsResponse(general);
        }

        return MatchingConditionResponse.builder()
                .buyerId(buyer.getBuyerId())
                .buyerName(buyer.getBuyerName())
                .buyerPhoneNumber(buyer.getBuyerPhoneNumber())
                .introductionFee(buyer.getIntroductionFee())
                .referralFee(buyer.getReferralFee())
                .postalCode(buyer.getPostalCode())
                .district(buyer.getDistrict())
                .city(buyer.getCity())
                .email(email)
                .branch(buyer.getBranch())
                .speciality(buyer.getSpeciality())
                .buyerEmails(buyerEmails)
                .buyerAreas(buyerAreas)
                .specialCarHandlingConditions(specialConditions)
                .generalCarHandlingConditions(generalConditions)
                .build();
    }


    private CarHandlingConditionsResponse mapToCarHandlingConditionsResponse(Object conditions) {
        if (conditions instanceof SpecialCarHandlingConditions special) {
            return CarHandlingConditionsResponse.builder()
                    .carOrigin(special.getCarOrigin())
                    .notMove(special.getNotMove())
                    .shopCeilMatchCount(special.getShopCeilMatchCount())
                    .carYearOkFrom(special.getCarYearOkFrom())
                    .carYearOkTo(special.getCarYearOkTo())
                    .carYearNgFrom(special.getCarYearNgFrom())
                    .carYearNgTo(special.getCarYearNgTo())
                    .carTravelDistanceOkFrom(special.getCarTravelDistanceOkFrom())
                    .carTravelDistanceOkTo(special.getCarTravelDistanceOkTo())
                    .carTravelDistanceNgFrom(special.getCarTravelDistanceNgFrom())
                    .carTravelDistanceNgTo(special.getCarTravelDistanceNgTo())
                    .exclusivity(special.getExclusivity())
                    .build();
        } else if (conditions instanceof GeneralCarHandlingConditions general) {
            return CarHandlingConditionsResponse.builder()
                    .carOrigin(general.getCarOrigin())
                    .notMove(general.getNotMove())
                    .shopCeilMatchCount(general.getShopCeilMatchCount())
                    .carYearOkFrom(general.getCarYearOkFrom())
                    .carYearOkTo(general.getCarYearOkTo())
                    .carYearNgFrom(general.getCarYearNgFrom())
                    .carYearNgTo(general.getCarYearNgTo())
                    .carTravelDistanceOkFrom(general.getCarTravelDistanceOkFrom())
                    .carTravelDistanceOkTo(general.getCarTravelDistanceOkTo())
                    .carTravelDistanceNgFrom(general.getCarTravelDistanceNgFrom())
                    .carTravelDistanceNgTo(general.getCarTravelDistanceNgTo())
                    .exclusivity(general.getExclusivity())
                    .build();
        }
        return null;

    }
}