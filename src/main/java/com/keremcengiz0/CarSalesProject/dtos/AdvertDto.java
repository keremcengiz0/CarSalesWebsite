package com.keremcengiz0.CarSalesProject.dtos;

import com.keremcengiz0.CarSalesProject.entities.User;
import com.keremcengiz0.CarSalesProject.entities.Vehicle;
import lombok.Data;

@Data
public class AdvertDto {
    private Long id;
    private String description;
    private Vehicle vehicle;
    private User user;
    private String title;
}
