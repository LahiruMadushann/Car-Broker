package com.example.seller.service;

import java.util.List;

public interface AssessedService {
    void saveAssessedDetails(List<Long> buyers, Long sellerId);
}
