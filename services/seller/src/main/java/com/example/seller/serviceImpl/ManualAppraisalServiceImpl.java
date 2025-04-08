package com.example.seller.serviceImpl;

import com.example.seller.dto.SellerCarDetailsRequest;
import com.example.seller.exception.AppraisalException;
import com.example.seller.exception.BadRequestException;
import com.example.seller.exception.DatabaseException;
import com.example.seller.mapper.AppraisalMapper;
import com.example.seller.repository.ManualAppraisalRepo;
import com.example.seller.service.ManualAppraisalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class ManualAppraisalServiceImpl implements ManualAppraisalService {
    private final ManualAppraisalRepo manualAppraisalRepo;
    private final AppraisalMapper appraisalMapper;

    @Override
    public Long createManualAppraisal(SellerCarDetailsRequest request) {
        try {
            var seller = manualAppraisalRepo.save(appraisalMapper.toSeller(request));
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
