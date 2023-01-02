package com.keremcengiz0.CarSalesProject.services;

import com.keremcengiz0.CarSalesProject.dtos.AdvertDto;
import com.keremcengiz0.CarSalesProject.dtos.UserDto;
import com.keremcengiz0.CarSalesProject.entities.User;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    User saveOneUser(User newUser);
    void deleteById(Long id) throws Exception;
    UserDto getOneUserById(Long id) throws Exception;

    List<AdvertDto> getOneUserAdverts(Long id);
}
