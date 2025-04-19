package com.example.buyer.dto;

import com.example.buyer.model.enums.CarOrigin;
import com.example.buyer.model.enums.Exclusivity;
import com.example.buyer.model.enums.NotMove;

public record CarHandlingConditionRequest(
        CarOrigin carOrigin,
        NotMove notMove,
        Integer shopCeilMatchCount,
        Long carYearOkFrom,
        Long carYearOkTo,
        Long carYearNgFrom,
        Long carYearNgTo,
        Long carTravelDistanceOkFrom,
        Long carTravelDistanceOkTo,
        Long carTravelDistanceNgFrom,
        Long carTravelDistanceNgTo,
        Exclusivity exclusivity
) {
}
