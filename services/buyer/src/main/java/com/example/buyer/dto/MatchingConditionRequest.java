package com.example.buyer.dto;

import java.util.List;

public record MatchingConditionRequest(
        List<String> buyerEmails,
        List<BuyerAreaRequest> buyerArea,
        CarHandlingConditionRequest carHandlingConditions,
        List<CarTypeRequest> carType,
        List<String> carMaker,
        List<String> bodyType,
        List<CarTypeNGRequest> ngCarType,
        List<String> ngCarMaker,
        List<String> ngBodyType
) {
}
