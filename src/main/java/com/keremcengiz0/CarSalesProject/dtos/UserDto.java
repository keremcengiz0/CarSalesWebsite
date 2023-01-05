package com.keremcengiz0.CarSalesProject.dtos;

import com.keremcengiz0.CarSalesProject.entities.Advert;
import com.keremcengiz0.CarSalesProject.entities.Role;
import lombok.Data;
import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String userName;
    private Role role;
    private List<Advert> adverts;
}
