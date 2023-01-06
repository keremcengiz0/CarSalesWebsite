package com.keremcengiz0.CarSalesProject.controllers;


import com.keremcengiz0.CarSalesProject.entities.RefreshToken;
import com.keremcengiz0.CarSalesProject.entities.User;
import com.keremcengiz0.CarSalesProject.requests.RefreshRequest;
import com.keremcengiz0.CarSalesProject.requests.UserRequest;
import com.keremcengiz0.CarSalesProject.responses.AuthResponse;
import com.keremcengiz0.CarSalesProject.security.JwtTokenProvider;
import com.keremcengiz0.CarSalesProject.services.RefreshTokenService;
import com.keremcengiz0.CarSalesProject.services.RefreshTokenServiceImpl;
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
    private RefreshTokenServiceImpl refreshTokenServiceImpl;


    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, PasswordEncoder passwordEncoder,
                          ModelMapper modelMapper, RefreshTokenServiceImpl refreshTokenServiceImpl) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.refreshTokenServiceImpl = refreshTokenServiceImpl;
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
        authResponse.setAccessToken("Bearer " + jwtToken);
        authResponse.setRefreshToken(this.refreshTokenServiceImpl.createRefreshToken(user));
        authResponse.setUserId(user.getId());

        return authResponse;

    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserRequest registerRequest) throws Exception {
        AuthResponse authResponse = new AuthResponse();
        this.userService.saveOneUser(registerRequest);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(registerRequest.getUserName(), registerRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        authResponse.setMessage("User successfully registered");
        authResponse.setAccessToken("Bearer " + jwtToken);

        User user = this.userService.getUserByUserName(registerRequest.getUserName());

        authResponse.setRefreshToken(this.refreshTokenServiceImpl.createRefreshToken(user));
        authResponse.setUserId(user.getId());
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);

    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshRequest refreshRequest) {
        AuthResponse response = new AuthResponse();
        RefreshToken token = this.refreshTokenServiceImpl.getByUser(refreshRequest.getId());
        if(token.getToken().equals(refreshRequest.getRefreshToken()) &&
                !this.refreshTokenServiceImpl.isRefreshExpired(token)) {

            User user = token.getUser();
            String jwtToken = this.jwtTokenProvider.generateJwtTokenByUserId(user.getId());
            response.setMessage("token successfully refreshed.");
            response.setAccessToken("Bearer " + jwtToken);
            response.setUserId(user.getId());
            return new ResponseEntity<>(response, HttpStatus.OK);

        } else {
            response.setMessage("refresh token is not valid.");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

    }


}
