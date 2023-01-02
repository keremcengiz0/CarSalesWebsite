package com.keremcengiz0.CarSalesProject.responses;

import com.keremcengiz0.CarSalesProject.dtos.UserDto;
import lombok.Data;

import java.util.List;

@Data
public class UserResponse {
    private List<UserDto> users;
}
