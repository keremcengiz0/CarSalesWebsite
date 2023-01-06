package com.keremcengiz0.CarSalesProject.requests;

import lombok.Data;

@Data
public class RefreshRequest {
    private Long id;
    private String refreshToken;
}
