package com.example.buyer.repository;

import com.example.buyer.model.BuyerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<BuyerDetails, Long> {
}
