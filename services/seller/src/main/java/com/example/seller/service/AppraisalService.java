package com.example.seller.service;

import com.example.seller.dto.SellerCarDetailsRequest;

public interface AppraisalService {
    Long createAppraisal(SellerCarDetailsRequest request);
}
