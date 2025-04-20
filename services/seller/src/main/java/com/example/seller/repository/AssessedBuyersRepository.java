package com.example.seller.repository;

import com.example.seller.model.AssessedBuyers;
import com.example.seller.model.EmbeddedAppraisalId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssessedBuyersRepository extends JpaRepository<AssessedBuyers, EmbeddedAppraisalId> {
}
