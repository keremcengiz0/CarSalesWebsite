package com.keremcengiz0.CarSalesProject.services;

import com.keremcengiz0.CarSalesProject.dtos.UserDto;
import com.keremcengiz0.CarSalesProject.entities.User;
import com.keremcengiz0.CarSalesProject.responses.UserResponse;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    User saveOneUser(User newUser);
    void deleteById(Long userId);
}
