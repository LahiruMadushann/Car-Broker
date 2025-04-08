package com.example.seller.service;

import com.example.seller.dto.SellerCarDetailsRequest;

public interface ManualAppraisalService {
    Long createManualAppraisal(SellerCarDetailsRequest request);
}
