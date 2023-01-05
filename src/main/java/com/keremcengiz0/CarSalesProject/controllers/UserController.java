package com.keremcengiz0.CarSalesProject.controllers;

import com.keremcengiz0.CarSalesProject.dtos.AdvertDto;
import com.keremcengiz0.CarSalesProject.dtos.UserDto;
import com.keremcengiz0.CarSalesProject.entities.User;
import com.keremcengiz0.CarSalesProject.requests.UserRequest;
import com.keremcengiz0.CarSalesProject.responses.GetAllUsersResponse;
import com.keremcengiz0.CarSalesProject.responses.GetOneUserAdvertsResponse;
import com.keremcengiz0.CarSalesProject.responses.GetOneUserResponse;
import com.keremcengiz0.CarSalesProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public GetAllUsersResponse getAllUserResponse() {
        List<UserDto> users = userService.getAllUsers();
        GetAllUsersResponse userResponse = new GetAllUsersResponse();
        userResponse.setUsers(users);
        return userResponse;
    }


    @GetMapping("/{userId}")
    public GetOneUserResponse getOneUserResponse(@PathVariable(name = "userId") Long id) throws Exception {
        UserDto userDto = this.userService.getOneUserById(id);
        GetOneUserResponse userResponse = new GetOneUserResponse();
        userResponse.setUserDto(userDto);
        return userResponse;
    }

    @GetMapping("/{userId}/adverts")
    public GetOneUserAdvertsResponse getOneUserAdvertsResponse(@PathVariable(name = "userId") Long id) {
        List<AdvertDto> advertDto = this.userService.getOneUserAdverts(id);
        GetOneUserAdvertsResponse getOneUserAdvertsResponse = new GetOneUserAdvertsResponse();
        getOneUserAdvertsResponse.setAdvertDto(advertDto);
        return getOneUserAdvertsResponse;
    }

    @PostMapping
    public User createUser(@RequestBody UserRequest newUser) {
        return this.userService.saveOneUser(newUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable(name = "userId") Long userId) throws Exception {
        this.userService.deleteById(userId);
    }


}
