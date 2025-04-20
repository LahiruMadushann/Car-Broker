package com.example.seller.repository;

import com.example.seller.model.SellerCarDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppraisalRepo extends JpaRepository<SellerCarDetails, Long> {
}
