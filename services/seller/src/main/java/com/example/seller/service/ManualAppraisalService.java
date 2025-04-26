package com.example.seller.service;

import com.example.seller.dto.SellerCarDetailsRequest;
import com.example.seller.dto.SellerCarDetailsResponse;

import java.util.List;

public interface ManualAppraisalService {
    Long createManualAppraisal(SellerCarDetailsRequest request);
    List<SellerCarDetailsResponse> getAllAppraisal(int offset, int limit);
}
