package com.keremcengiz0.CarSalesProject.services;

import com.keremcengiz0.CarSalesProject.entities.RefreshToken;
import com.keremcengiz0.CarSalesProject.entities.User;
import com.keremcengiz0.CarSalesProject.repositories.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Value("${refresh.token.expires.in}")
    private Long expireSeconds;
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public boolean isRefreshExpired(RefreshToken refreshToken) {

        return refreshToken.getExpiryDate().before(new Date());
    }

    public RefreshToken getByUser(Long id) {
        return this.refreshTokenRepository.findByUserId(id);
    }

    public String createRefreshToken(User user) {
        RefreshToken token = this.refreshTokenRepository.findByUserId(user.getId());

        if(token == null) {
            token =	new RefreshToken();
            token.setUser(user);
        }
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(Date.from(Instant.now().plusMillis(expireSeconds)));
        this.refreshTokenRepository.save(token);
        return token.getToken();
    }




}
