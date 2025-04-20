package com.example.seller.serviceImpl;

import com.example.seller.dto.SellerCarDetailsRequest;
import com.example.seller.exception.AppraisalException;
import com.example.seller.exception.BadRequestException;
import com.example.seller.exception.DatabaseException;
import com.example.seller.mapper.AppraisalMapper;
import com.example.seller.repository.AppraisalRepo;
import com.example.seller.service.AppraisalService;
import com.example.seller.service.AssessedService;
import com.example.seller.service.BuyerServiceClient;
import com.example.seller.service.MatchingConditionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class AppraisalServiceImpl implements AppraisalService {

    private final AppraisalRepo appraisalRepo;
    private final AppraisalMapper appraisalMapper;
    private final BuyerServiceClient buyerServiceClient;
    private final MatchingConditionService matchingConditionService;
    private final AssessedService assessedService;

    @Override
    public Long createAppraisal(SellerCarDetailsRequest request) {
        try {
            var seller = appraisalRepo.save(appraisalMapper.toSeller(request));
            var matchingConditions = buyerServiceClient.getAllMatchingConditions();
            var matchedShopIds = matchingConditionService.automaticMatch(matchingConditions,seller);
            assessedService.saveAssessedDetails(matchedShopIds, seller.getAppraisalId());
            return seller.getAppraisalId();
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException("Invalid data: " + Objects.requireNonNull(e.getRootCause()).getMessage());
        } catch (DataAccessException e) {
            throw new DatabaseException("Database error occurred while saving appraisal");
        } catch (Exception e) {
            throw new AppraisalException("Unexpected error occurred", e);
        }
    }
}
