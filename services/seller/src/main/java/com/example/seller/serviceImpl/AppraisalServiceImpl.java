package com.example.seller.serviceImpl;

import com.example.seller.dto.SellerCarDetailsRequest;
import com.example.seller.exception.AppraisalException;
import com.example.seller.exception.BadRequestException;
import com.example.seller.exception.DatabaseException;
import com.example.seller.mapper.AppraisalMapper;
import com.example.seller.repository.AppraisalRepo;
import com.example.seller.service.AppraisalService;
import com.example.seller.service.AssessedService;
import com.example.seller.service.client.BuyerServiceClient;
import com.example.seller.service.MatchingConditionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class AppraisalServiceImpl implements AppraisalService {

    private final AppraisalRepo appraisalRepo;
    private final AppraisalMapper appraisalMapper;
    private final BuyerServiceClient buyerServiceClient;
    private final MatchingConditionService matchingConditionService;
    private final AssessedService assessedService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${spring.kafka.topic.appraisal-requests}")
    private String appraisalRequestsTopic;

    @Override
    public String queueAppraisalRequest(SellerCarDetailsRequest request) {
        String requestId = UUID.randomUUID().toString();

        try {
            log.info("Publishing appraisal request with ID: {}", requestId);
            CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(
                    appraisalRequestsTopic,
                    requestId,
                    request
            );

            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    log.info("Sent appraisal request with ID: {}", requestId);
                } else {
                    log.error("Unable to send appraisal request with ID: {}", requestId, ex);
                    throw new AppraisalException("Unable to send appraisal request with ID: " + requestId, ex);
                }
            });

            return requestId;
        } catch (Exception e) {
            log.error("Error while publishing appraisal request: {}", e.getMessage(), e);
            throw new AppraisalException("Failed to queue appraisal request", e);
        }
    }

    @Override
    public Long processAppraisalRequest(SellerCarDetailsRequest request) {
        try {
            var seller = appraisalRepo.save(appraisalMapper.toSeller(request));
            var matchingConditions = buyerServiceClient.getAllMatchingConditions();
            var matchedShopIds = matchingConditionService.automaticMatch(matchingConditions, seller);
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
