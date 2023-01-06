package com.keremcengiz0.CarSalesProject.services;

import com.keremcengiz0.CarSalesProject.dtos.AdvertDto;
import com.keremcengiz0.CarSalesProject.dtos.UserDto;
import com.keremcengiz0.CarSalesProject.entities.User;
import com.keremcengiz0.CarSalesProject.requests.UserRequest;
import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    User saveOneUser(UserRequest newUser) throws Exception;
    void deleteById(Long id) throws Exception;
    UserDto getOneUserById(Long id) throws Exception;
    UserDto getOneUserByUserName(String userName) throws Exception;
    List<AdvertDto> getOneUserAdverts(Long id);
    User getUserByUserName(String userName);
}
