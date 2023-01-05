package com.keremcengiz0.CarSalesProject.services;

import com.keremcengiz0.CarSalesProject.entities.User;
import com.keremcengiz0.CarSalesProject.repositories.UserRepository;
import com.keremcengiz0.CarSalesProject.security.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUserName(username);
        return JwtUserDetails.create(user);
    }

    public UserDetails loadUserById(Long id) {
        User user = this.userRepository.findById(id).get();
        return JwtUserDetails.create(user);
    }
}
