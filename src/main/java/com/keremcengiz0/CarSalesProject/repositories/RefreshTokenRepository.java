package com.keremcengiz0.CarSalesProject.repositories;

import com.keremcengiz0.CarSalesProject.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    RefreshToken findByUserId(Long id);
}
