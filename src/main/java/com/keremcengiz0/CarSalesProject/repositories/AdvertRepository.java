package com.keremcengiz0.CarSalesProject.repositories;

import com.keremcengiz0.CarSalesProject.dtos.AdvertDto;
import com.keremcengiz0.CarSalesProject.entities.Advert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdvertRepository extends JpaRepository<Advert, Long> {
    @Query(value = "Select a.* from advert as a "
            + "inner join vehicle as v on v.id = a.id "
            + "where v.brand =:brand", nativeQuery = true)
    List<Advert> getAllAdvertsByBrand(@Param("brand") String brand);

}
