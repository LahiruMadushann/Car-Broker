package com.example.buyer.serviceImpl;

import com.example.buyer.dto.MatchingConditionRequest;
import com.example.buyer.dto.MatchingConditionResponse;
import com.example.buyer.exception.BuyerException;
import com.example.buyer.exception.DatabaseException;
import com.example.buyer.mapper.MatchingConditionMapper;
import com.example.buyer.model.BuyerDetails;
import com.example.buyer.model.enums.Speciality;
import com.example.buyer.repository.BuyerRepository;
import com.example.buyer.service.MatchingConditionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MatchingConditionServiceImpl implements MatchingConditionService {
    private final BuyerRepository buyerRepository;
    private final MatchingConditionMapper matchingConditionMapper;

    @Override
    @Transactional
    public Long createMatchingConditions(Long buyerId, Speciality speciality, MatchingConditionRequest matchingConditionRequest) {
        log.info("Creating {} matching conditions for buyer with ID: {}", speciality, buyerId);

        BuyerDetails existingBuyer = buyerRepository.findById(buyerId)
                .orElseThrow(() -> new EntityNotFoundException("Buyer not found with ID: " + buyerId));

        BuyerDetails updatedBuyer;

        if (speciality == Speciality.SPECIAL) {
            BuyerDetails newConditions = matchingConditionMapper.toSpecialMatchingCondition(matchingConditionRequest);

            newConditions.setBuyerId(existingBuyer.getBuyerId());
            newConditions.setBuyerName(existingBuyer.getBuyerName());
            newConditions.setBuyerPhoneNumber(existingBuyer.getBuyerPhoneNumber());
            newConditions.setIntroductionFee(existingBuyer.getIntroductionFee());
            newConditions.setReferralFee(existingBuyer.getReferralFee());
            newConditions.setPostalCode(existingBuyer.getPostalCode());
            newConditions.setDistrict(existingBuyer.getDistrict());
            newConditions.setCity(existingBuyer.getCity());
            newConditions.setBranch(existingBuyer.getBranch());
            newConditions.setSpeciality(existingBuyer.getSpeciality());
            newConditions.setBuyerAccountDetails(existingBuyer.getBuyerAccountDetails());

            updatedBuyer = buyerRepository.save(newConditions);

        } else if (speciality == Speciality.GENERAL) {
            BuyerDetails newConditions = matchingConditionMapper.toGeneralMatchingCondition(matchingConditionRequest);

            newConditions.setBuyerId(existingBuyer.getBuyerId());
            newConditions.setBuyerName(existingBuyer.getBuyerName());
            newConditions.setBuyerPhoneNumber(existingBuyer.getBuyerPhoneNumber());
            newConditions.setIntroductionFee(existingBuyer.getIntroductionFee());
            newConditions.setReferralFee(existingBuyer.getReferralFee());
            newConditions.setPostalCode(existingBuyer.getPostalCode());
            newConditions.setDistrict(existingBuyer.getDistrict());
            newConditions.setCity(existingBuyer.getCity());
            newConditions.setBranch(existingBuyer.getBranch());
            newConditions.setSpeciality(existingBuyer.getSpeciality());
            newConditions.setBuyerAccountDetails(existingBuyer.getBuyerAccountDetails());

            updatedBuyer = buyerRepository.save(newConditions);

        } else {
            throw new IllegalArgumentException("Unsupported speciality: " + speciality);
        }

        log.info("Successfully created matching conditions for buyer with ID: {}", buyerId);
        return updatedBuyer.getBuyerId();
    }

    @Override
    public List<MatchingConditionResponse> getAllMatchingConditions() {
        try {

            return buyerRepository.findAll()
                    .stream()
                    .map(matchingConditionMapper::fromMatchingCondition)
                    .toList();

        } catch (DataAccessException e) {
            log.error("Database access error while fetching all matching conditions", e);
            throw new DatabaseException("Database error occurred while retrieving matching conditions");
        } catch (Exception e) {
            log.error("Unexpected error while fetching all matching conditions", e);
            throw new BuyerException("Unexpected error occurred while retrieving matching conditions", e);
        }
    }
}
