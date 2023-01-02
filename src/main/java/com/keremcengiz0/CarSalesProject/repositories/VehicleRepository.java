package com.keremcengiz0.CarSalesProject.repositories;

import com.keremcengiz0.CarSalesProject.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
