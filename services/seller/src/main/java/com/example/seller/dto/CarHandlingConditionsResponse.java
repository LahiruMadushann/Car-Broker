package com.example.seller.dto;

import com.example.seller.model.enums.CarOrigin;
import com.example.seller.model.enums.Exclusivity;
import com.example.seller.model.enums.NotMove;

public record CarHandlingConditionsResponse(
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
