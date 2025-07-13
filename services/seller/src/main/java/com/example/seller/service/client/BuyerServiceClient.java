package com.example.seller.service.client;

import com.example.seller.dto.BuyerDetails;
import com.example.seller.dto.MatchingConditionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "BUYER-SERVICE", path = "/api/v1/buyer")
public interface BuyerServiceClient {

    @GetMapping("/all")
    List<BuyerDetails> getAllBuyers();

    @GetMapping("/matching-conditions")
    List<MatchingConditionResponse> getAllMatchingConditions();

}
