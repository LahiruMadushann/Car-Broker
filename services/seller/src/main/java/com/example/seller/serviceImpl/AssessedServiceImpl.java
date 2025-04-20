package com.example.seller.serviceImpl;

import com.example.seller.mapper.AssessedBuyersMapper;
import com.example.seller.model.AssessedBuyers;
import com.example.seller.model.EmbeddedAppraisalId;
import com.example.seller.model.SellerCarDetails;
import com.example.seller.repository.AssessedBuyersRepository;
import com.example.seller.service.AssessedService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AssessedServiceImpl implements AssessedService {

    private final AssessedBuyersMapper assessedBuyersMapper;
    private final AssessedBuyersRepository assessedBuyersRepository;
    @Override
    public void saveAssessedDetails(List<Long> buyers, Long sellerId) {

        LocalDateTime now = LocalDateTime.now();

        buyers.forEach(buyerId -> {
            EmbeddedAppraisalId embeddedId = new EmbeddedAppraisalId();
            embeddedId.setShopId(Math.toIntExact(buyerId));
            embeddedId.setAppraisalId(sellerId);

            Optional<AssessedBuyers> existingRecord = assessedBuyersRepository.findById(embeddedId);

            if (existingRecord.isPresent()) {
                AssessedBuyers existing = existingRecord.get();
                existing.setAssessedDateTime(now);
                existing.setAssessedStatus("PENDING");
                assessedBuyersRepository.save(existing);
            } else {
                AssessedBuyers assessedBuyer = assessedBuyersMapper.toEntity(buyerId, sellerId);
                assessedBuyer.setEmbeddedAppraisalId(embeddedId);
                assessedBuyer.setAssessedDateTime(now);
                assessedBuyer.setAssessedStatus("PENDING");
                assessedBuyer.setReject(false);
                assessedBuyersRepository.save(assessedBuyer);
            }
        });
    }
}
