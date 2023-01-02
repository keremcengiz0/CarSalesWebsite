package com.keremcengiz0.CarSalesProject.services;

import com.keremcengiz0.CarSalesProject.dtos.UserDto;
import com.keremcengiz0.CarSalesProject.entities.User;
import com.keremcengiz0.CarSalesProject.repositories.UserRepository;
import com.keremcengiz0.CarSalesProject.responses.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return this.userRepository.findAll().stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public User saveOneUser(User newUser) {
        return this.userRepository.save(newUser);
    }


    @Override
    public void deleteById(Long userId) {
        Optional<User> user = this.userRepository.findById(userId);
        if (user.isPresent()) {
            User foundUserToDelete = user.get();
            this.userRepository.deleteById(foundUserToDelete.getId());
        }
    }

}
