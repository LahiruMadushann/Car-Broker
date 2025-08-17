package com.example.buyer.serviceImpl;

import com.example.buyer.dto.BuyerRegistrationRequest;
import com.example.buyer.dto.BuyerRegistrationResponse;
import com.example.buyer.dto.MatchingConditionRequest;
import com.example.buyer.exception.BadRequestException;
import com.example.buyer.exception.BuyerException;
import com.example.buyer.exception.DatabaseException;
import com.example.buyer.mapper.BuyerMapper;
import com.example.buyer.model.BuyerDetails;
import com.example.buyer.model.enums.Speciality;
import com.example.buyer.repository.BuyerRepository;
import com.example.buyer.service.BuyerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class BuyerServiceImpl implements BuyerService {

    private final BuyerRepository buyerRepository;
    private final BuyerMapper buyerMapper;

    @Override
    public Long createBuyer(BuyerRegistrationRequest buyerRegistrationRequest) {
        try {
            var buyer = buyerRepository.save(buyerMapper.toBuyer(buyerRegistrationRequest));
            return buyer.getBuyerId();
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException("Invalid data: " + Objects.requireNonNull(e.getRootCause()).getMessage());
        } catch (DataAccessException e) {
            throw new DatabaseException("Database error occurred while saving buyer");
        } catch (Exception e) {
            throw new BuyerException("Unexpected error occurred", e);
        }
    }

    @Override
    public List<BuyerRegistrationResponse> getAllBuyers() {
        try {

            return buyerRepository.findAll()
                    .stream()
                    .map(buyerMapper::fromBuyer)
                    .toList();

        } catch (DataAccessException e) {
            log.error("Database access error while fetching all buyers", e);
            throw new DatabaseException("Database error occurred while retrieving buyers");
        } catch (Exception e) {
            log.error("Unexpected error while fetching all buyers", e);
            throw new BuyerException("Unexpected error occurred while retrieving buyers", e);
        }
    }

    @Override
    public Page<BuyerRegistrationResponse> getAllBuyersPaginated(Integer page, Integer size, String sortBy, String sortDir) {
        try {
            int pageNumber = (page != null && page >= 0) ? page : 0;
            int pageSize = (size != null && size >0 && size <= 100)  ? size : 10;

            Pageable pageable;
            if (sortBy != null && !sortBy.trim().isEmpty()) {
                String validatedSortBy = validateSortField(sortBy);
                Sort sort = createSort(validatedSortBy, sortDir);
                pageable = PageRequest.of(pageNumber, pageSize, sort);
                log.info("Retrieved buyers with pagination and sorting - page: {}, size: {}, sortBy: {}, sortDir: {}", pageNumber, pageSize, validatedSortBy, sortDir);
            } else {
                pageable = PageRequest.of(pageNumber, pageSize);
                log.info("Retrieved buyers with pagination only - page: {}, size: {}", pageNumber, pageSize);
            }
            Page<BuyerDetails> buyerPage = buyerRepository.findAll(pageable);
            return buyerPage.map(buyerMapper::fromBuyer);

        } catch (DataAccessException e) {
            log.error("Database access error while fetching paginated buyers - page: {}, size: {}, sortBy: {}, sortDir: {}", page, size, sortBy, sortDir, e);
            throw new DatabaseException("Database error occurred while retrieving buyers");
        } catch (IllegalArgumentException e) {
            log.error("Invalid parameters for pagination: {}", e.getMessage());
            throw new BadRequestException("Invalid parameters for pagination");
        } catch (Exception e) {
            log.error("Unexpected error while fetching paginated buyers - page: {}, size: {}, sortBy: {}, sortDir: {}", page, size, sortBy, sortDir, e);
            throw new BuyerException("Unexpected error occurred while retrieving buyers", e);
        }
    }

    private Sort createSort(String sortBy, String sortDir) {
        return (sortDir != null && sortDir.equalsIgnoreCase("desc"))
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
    }

    private String validateSortField(String sortBy) {
        Set<String> allowedSortFields = Set.of(
                "buyerId",
                "buyerName",
                "buyerPhoneNumber",
                "introductionFee",
                "referralFee",
                "postalCode",
                "district",
                "city",
                "branch",
                "speciality",
                "createdAt",
                "updatedAt"
        );

        if (!allowedSortFields.contains(sortBy)) {
            log.warn("Invalid sort field requested: {}, defaulting to buyerId", sortBy);
            return "buyerId";
        }

        return sortBy;
    }
}
