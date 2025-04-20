package com.example.seller.service;

import com.example.seller.dto.SellerCarDetailsRequest;

public interface AppraisalService {
    String queueAppraisalRequest(SellerCarDetailsRequest request);
    Long processAppraisalRequest(SellerCarDetailsRequest request);
}
