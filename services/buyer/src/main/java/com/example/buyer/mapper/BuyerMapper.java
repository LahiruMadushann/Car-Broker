package com.example.buyer.mapper;

import com.example.buyer.dto.BuyerRegistrationRequest;
import com.example.buyer.model.BuyerAccountDetails;
import com.example.buyer.model.BuyerDetails;
import com.example.buyer.service.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class BuyerMapper {

    private final String SECRET_KEY = Base64.getEncoder().encodeToString(new SecureRandom().generateSeed(32));

    private final PasswordService passwordService;

    public BuyerDetails toBuyer(BuyerRegistrationRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("sellerCarDetailsRequest cannot be null");
        }

        BuyerAccountDetails buyerAccountDetails = BuyerAccountDetails.builder()
                .buyerUserName(request.email())
                .email(request.email())
                .password(passwordService.hashPassword(SECRET_KEY))
                .build();

        return BuyerDetails.builder()
                .buyerName(request.buyerName())
                .buyerPhoneNumber(request.buyerPhoneNumber())
                .introductionFee(request.introductionFee())
                .referralFee(request.referralFee())
                .postalCode(request.postalCode())
                .district(request.district())
                .city(request.city())
                .branch(request.branch())
                .speciality(request.speciality())
                .buyerAccountDetails(buyerAccountDetails)
                .build();
    }
}
