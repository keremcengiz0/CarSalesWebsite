package com.keremcengiz0.CarSalesProject.repositories;

import com.keremcengiz0.CarSalesProject.entities.Advert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertRepository extends JpaRepository<Advert, Long> {
}
