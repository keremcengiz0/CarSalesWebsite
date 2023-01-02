package com.keremcengiz0.CarSalesProject.repositories;

import com.keremcengiz0.CarSalesProject.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
