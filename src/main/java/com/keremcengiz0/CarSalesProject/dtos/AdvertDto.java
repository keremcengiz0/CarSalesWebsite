package com.keremcengiz0.CarSalesProject.dtos;

import com.keremcengiz0.CarSalesProject.entities.Image;
import com.keremcengiz0.CarSalesProject.entities.Vehicle;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AdvertDto {
    private Long id;
    private LocalDate advertDate;
    private String description;
    private Vehicle vehicle;
    private String title;
    private List<Image> images;
}
