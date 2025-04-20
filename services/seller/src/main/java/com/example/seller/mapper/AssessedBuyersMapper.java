package com.example.seller.mapper;

import com.example.seller.model.AssessedBuyers;
import com.example.seller.model.EmbeddedAppraisalId;
import org.springframework.stereotype.Service;

@Service
public class AssessedBuyersMapper {
    public AssessedBuyers toEntity(Long buyerId, Long sellerId) {
        EmbeddedAppraisalId embeddedId = new EmbeddedAppraisalId();
        embeddedId.setShopId(Math.toIntExact(buyerId));
        embeddedId.setAppraisalId(sellerId);

        return AssessedBuyers.builder()
                .embeddedAppraisalId(embeddedId)
                .assessedStatus("PENDING")
                .reject(false)
                .build();
    }

}
