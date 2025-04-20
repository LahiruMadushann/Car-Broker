package com.example.seller.service;

import com.example.seller.dto.BuyerDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "BUYER-SERVICE", path = "/api/v1/buyer")
public interface BuyerServiceClient {

    @GetMapping("/all")
    List<BuyerDetails> getAllBuyers();

    @GetMapping("/matching-conditions")
    List<MatchingConditionResponse> getAllMatchingConditions();

}
