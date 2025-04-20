package com.example.seller.serviceImpl;

import com.example.seller.dto.CarHandlingConditionsResponse;
import com.example.seller.dto.MatchingConditionResponse;
import com.example.seller.model.CarDetails;
import com.example.seller.model.SellerCarDetails;
import com.example.seller.model.enums.CarOrigin;
import com.example.seller.service.MatchingConditionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MatchingConditionServiceImpl implements MatchingConditionService {
    List<Long> matchedBuyerIds = new ArrayList<>();
    @Override
    public List<Long> automaticMatch(List<MatchingConditionResponse> matchingConditionResponse, SellerCarDetails sellerCarDetails) {
        List<MatchingConditionResponse> filteredBuyers = matchingConditionResponse.stream()
                .filter(buyer -> {
                    boolean locationMatch = buyer.buyerAreas().stream()
                            .anyMatch(area ->
                                    area.district().equals(sellerCarDetails.getSellerDetails().getSellerDistrict()) &&
                                            area.city().equals(sellerCarDetails.getSellerDetails().getSellerCity())
                            );

                    CarDetails carDetails = sellerCarDetails.getCarDetails();

                    CarHandlingConditionsResponse conditions = buyer.specialCarHandlingConditions();
                    if (conditions == null) {
                        conditions = buyer.generalCarHandlingConditions();
                    }

                    if (conditions == null) {
                        return locationMatch;
                    }

                    boolean carYearOk = true;
                    long carYear = Long.parseLong(carDetails.getCarModelYear());
                    if (conditions.carYearOkFrom() != null && conditions.carYearOkTo() != null) {
                        carYearOk = carYear >= conditions.carYearOkFrom() && carYear <= conditions.carYearOkTo();
                    }

                    boolean carYearNotNG = true;
                    if (conditions.carYearNgFrom() != null && conditions.carYearNgTo() != null) {
                        carYearNotNG = !(carYear >= conditions.carYearNgFrom() && carYear <= conditions.carYearNgTo());
                    }

                    boolean distanceOk = true;
                    long carDistance = Long.parseLong(carDetails.getCarTraveledDistance());
                    if (conditions.carTravelDistanceOkFrom() != null && conditions.carTravelDistanceOkTo() != null) {
                        distanceOk = carDistance >= conditions.carTravelDistanceOkFrom() &&
                                carDistance <= conditions.carTravelDistanceOkTo();
                    }

                    boolean distanceNotNG = true;
                    if (conditions.carTravelDistanceNgFrom() != null && conditions.carTravelDistanceNgTo() != null) {
                        distanceNotNG = !(carDistance >= conditions.carTravelDistanceNgFrom() &&
                                carDistance <= conditions.carTravelDistanceNgTo());
                    }

                    boolean carOriginMatch = conditions.carOrigin() == (buyer.specialCarHandlingConditions() != null ? buyer.specialCarHandlingConditions().carOrigin() : null);

                    boolean carMatch = carYearOk && carYearNotNG && distanceOk && distanceNotNG && carOriginMatch;

                    return locationMatch && carMatch;
                })
                .collect(Collectors.toList());
        matchedBuyerIds = filteredBuyers.stream().map(MatchingConditionResponse::buyerId).collect(Collectors.toList());
        return matchedBuyerIds;
    }
}
