package com.keremcengiz0.CarSalesProject.dtos;

import com.keremcengiz0.CarSalesProject.entities.Advert;
import lombok.Data;
import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String userName;
    private List<Advert> adverts;
}
