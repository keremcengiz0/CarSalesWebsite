package com.keremcengiz0.CarSalesProject.responses;

import lombok.Data;

@Data
public class AuthResponse {
    private String message;
    private Long userId;
    String accessToken;
    String refreshToken;
}
