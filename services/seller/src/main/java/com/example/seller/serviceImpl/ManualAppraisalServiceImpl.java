package com.example.seller.serviceImpl;

import com.example.seller.dto.SellerCarDetailsRequest;
import com.example.seller.service.ManualAppraisalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ManualAppraisalServiceImpl implements ManualAppraisalService {
    @Override
    public Long createManualAppraisal(SellerCarDetailsRequest request) {
        return 0L;
    }
}
