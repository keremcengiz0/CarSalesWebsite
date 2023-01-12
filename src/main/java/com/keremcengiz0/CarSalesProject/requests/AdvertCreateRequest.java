package com.keremcengiz0.CarSalesProject.requests;

import com.keremcengiz0.CarSalesProject.entities.Image;
import com.keremcengiz0.CarSalesProject.entities.User;
import com.keremcengiz0.CarSalesProject.entities.Vehicle;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AdvertCreateRequest {
    private Long userId;
    private String description;
    private Vehicle vehicle;
    private String title;
    private List<Image> images;
}
