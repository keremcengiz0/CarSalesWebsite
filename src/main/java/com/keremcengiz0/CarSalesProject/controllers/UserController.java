package com.keremcengiz0.CarSalesProject.controllers;

import com.keremcengiz0.CarSalesProject.dtos.UserDto;
import com.keremcengiz0.CarSalesProject.entities.User;
import com.keremcengiz0.CarSalesProject.responses.UserResponse;
import com.keremcengiz0.CarSalesProject.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {


    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public UserResponse getAllUserResponse() {
        List<UserDto> users = userService.getAllUsers();
        UserResponse userResponse = new UserResponse();
        userResponse.setUsers(users);
        return userResponse;
    }

    @PostMapping
    public User createUser(@RequestBody User newUser) {
        return this.userService.saveOneUser(newUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId) {
        this.userService.deleteById(userId);
    }


}
