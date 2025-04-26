package com.example.seller.serviceImpl;

import com.example.seller.dto.SellerCarDetailsRequest;
import com.example.seller.dto.SellerCarDetailsResponse;
import com.example.seller.exception.AppraisalException;
import com.example.seller.exception.BadRequestException;
import com.example.seller.exception.DatabaseException;
import com.example.seller.mapper.AppraisalMapper;
import com.example.seller.model.SellerCarDetails;
import com.example.seller.repository.ManualAppraisalRepo;
import com.example.seller.service.ManualAppraisalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
            throw new AppraisalException("Unexpected error occurred saving appraisal data", e);
        }
    }

    @Override
    public List<SellerCarDetailsResponse> getAllAppraisal(int offset, int limit) {
        try {
            PageRequest pageRequest = PageRequest.of(offset, limit);
            Page<SellerCarDetails> appraisals = manualAppraisalRepo.findAll(pageRequest);

            return appraisals.getContent().stream()
                    .map(appraisalMapper::fromSeller)
                    .collect(Collectors.toList());

        } catch (DataAccessException e) {
            throw new DatabaseException("Database error occurred while getting appraisal details");
        } catch (Exception e) {
            throw new AppraisalException("Unexpected error occurred while getting appraisal details", e);
        }
    }
}
