package com.keremcengiz0.CarSalesProject.controllers;


import com.keremcengiz0.CarSalesProject.entities.User;
import com.keremcengiz0.CarSalesProject.requests.UserRequest;
import com.keremcengiz0.CarSalesProject.responses.AuthResponse;
import com.keremcengiz0.CarSalesProject.security.JwtTokenProvider;
import com.keremcengiz0.CarSalesProject.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    private ModelMapper modelMapper;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody UserRequest loginRequest) throws Exception {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getUserName(), loginRequest.getPassword());

        Authentication auth = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);

        User user = this.userService.getUserByUserName(loginRequest.getUserName());

        AuthResponse authResponse = new AuthResponse();
        authResponse.setMessage("Bearer " + jwtToken);
        authResponse.setUserId(user.getId());

        return authResponse;

    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserRequest registerRequest) throws Exception {
        AuthResponse authResponse = new AuthResponse();
        this.userService.saveOneUser(registerRequest);
        authResponse.setMessage("User successfully registered");

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);

    }


}
