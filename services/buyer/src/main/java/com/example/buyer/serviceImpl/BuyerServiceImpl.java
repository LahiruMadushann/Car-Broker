package com.example.buyer.serviceImpl;

import com.example.buyer.dto.BuyerRegistrationRequest;
import com.example.buyer.exception.BadRequestException;
import com.example.buyer.exception.BuyerException;
import com.example.buyer.exception.DatabaseException;
import com.example.buyer.mapper.BuyerMapper;
import com.example.buyer.repository.BuyerRepository;
import com.example.buyer.service.BuyerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
}
