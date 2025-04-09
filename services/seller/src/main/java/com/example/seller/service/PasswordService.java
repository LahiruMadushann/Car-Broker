package com.example.seller.service;

public interface PasswordService {
    String hashPassword(String rawPassword);
    boolean verifyPassword(String rawPassword, String hashedPassword);
}
