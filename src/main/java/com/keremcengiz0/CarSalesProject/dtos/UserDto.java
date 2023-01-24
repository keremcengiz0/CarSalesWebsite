package com.keremcengiz0.CarSalesProject.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.keremcengiz0.CarSalesProject.entities.Advert;
import com.keremcengiz0.CarSalesProject.entities.Role;
import lombok.Data;
import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String telNo;
    private Role role;
    private List<Advert> adverts;
}
