package com.keremcengiz0.CarSalesProject.services;

import com.keremcengiz0.CarSalesProject.dtos.UserDto;
import com.keremcengiz0.CarSalesProject.entities.User;
import com.keremcengiz0.CarSalesProject.repositories.UserRepository;
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
    public UserDto getOneUserById(Long id) throws Exception {
        Optional<User> userOptional = this.userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new Exception("User not found!");
        }

        User user = userOptional.get();

        return this.modelMapper.map(user, UserDto.class);
    }

    @Override
    public User saveOneUser(User newUser) {
        return this.userRepository.save(newUser);
    }


    @Override
    public void deleteById(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            User foundUserToDelete = user.get();
            this.userRepository.deleteById(foundUserToDelete.getId());
        }
    }


}
