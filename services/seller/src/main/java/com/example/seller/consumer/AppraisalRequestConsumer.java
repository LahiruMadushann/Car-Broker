package com.example.seller.consumer;

import com.example.seller.dto.SellerCarDetailsRequest;
import com.example.seller.service.AppraisalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AppraisalRequestConsumer {

    private final AppraisalService appraisalService;

    @KafkaListener(
            topics = "${spring.kafka.topic.appraisal-requests}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(@Payload SellerCarDetailsRequest request) {
        log.info("Received appraisal request for processing");
        try {
            Long appraisalId = appraisalService.processAppraisalRequest(request);
            log.info("Successfully processed appraisal request. Appraisal ID: {}", appraisalId);
        } catch (Exception e) {
            log.error("Error processing appraisal request: {}", e.getMessage(), e);
        }
    }
}

