package com.example.buyer.service;

public interface PasswordService {
    String hashPassword(String rawPassword);
    boolean verifyPassword(String rawPassword, String hashedPassword);
}
