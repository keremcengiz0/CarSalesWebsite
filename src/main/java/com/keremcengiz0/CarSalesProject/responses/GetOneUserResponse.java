package com.keremcengiz0.CarSalesProject.responses;

import com.keremcengiz0.CarSalesProject.dtos.UserDto;
import lombok.Data;

@Data
public class GetOneUserResponse {
    private UserDto userDto;
}
