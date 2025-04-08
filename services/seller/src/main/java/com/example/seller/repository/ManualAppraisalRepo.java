package com.example.seller.repository;

import com.example.seller.model.SellerCarDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManualAppraisalRepo extends JpaRepository<SellerCarDetails, Long> {
}
