package com.keremcengiz0.CarSalesProject.services;

import com.keremcengiz0.CarSalesProject.dtos.AdvertDto;
import com.keremcengiz0.CarSalesProject.dtos.UserDto;
import com.keremcengiz0.CarSalesProject.entities.Advert;
import com.keremcengiz0.CarSalesProject.entities.Role;
import com.keremcengiz0.CarSalesProject.entities.User;
import com.keremcengiz0.CarSalesProject.repositories.AdvertRepository;
import com.keremcengiz0.CarSalesProject.repositories.UserRepository;
import com.keremcengiz0.CarSalesProject.requests.UserRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private AdvertRepository advertRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, AdvertRepository advertRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.advertRepository = advertRepository;
        this.passwordEncoder = passwordEncoder;
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
    public UserDto getOneUserByUserName(String userName) throws Exception {
        Optional<User> userOptional = Optional.ofNullable(this.userRepository.findByUserName(userName));

        if (userOptional.isEmpty()) {
            throw new Exception("User not found!");
        }

        User user = userOptional.get();

        return this.modelMapper.map(user, UserDto.class);

    }

    @Override
    public User getUserByUserName(String userName) {
        return this.userRepository.findByUserName(userName);
    }

    @Override
    public List<AdvertDto> getOneUserAdverts(Long id) throws Exception {

        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()) {
            throw new Exception("User not found!");
        }

        List<Advert> adverts = advertRepository.findAdvertsByUserId(id);


        return this.advertRepository.findAdvertsByUserId(id).stream().map(advert -> modelMapper.map(advert,AdvertDto.class)).collect(Collectors.toList());
    }



    @Override
    public User saveOneUser(UserRequest newUser) throws Exception {

        if (userRepository.findByUserName(newUser.getUserName()) != null) {
            throw new Exception("Username already in use");
        }

        User user = new User();
        user.setUserName(newUser.getUserName());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setTelNo(newUser.getTelNo());
        user.setRole(Role.USER);
        return this.userRepository.save(user);
    }


    @Override
    public void deleteById(Long id) throws Exception {
        Optional<User> user = this.userRepository.findById(id);

        if (user.isEmpty()) {
            throw new Exception("User not found!");
        }
        User foundUserToDelete = user.get();
        this.userRepository.deleteById(foundUserToDelete.getId());
    }




}
