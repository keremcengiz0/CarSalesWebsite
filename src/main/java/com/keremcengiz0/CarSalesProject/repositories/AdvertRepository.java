package com.keremcengiz0.CarSalesProject.repositories;

import com.keremcengiz0.CarSalesProject.entities.Advert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface AdvertRepository extends JpaRepository<Advert, Long> {
    @Query(value = "Select a.* from advert as a "
            + "inner join vehicle as v on v.id = a.id "
            + "where v.brand =:brand", nativeQuery = true)
    List<Advert> getAllAdvertsByBrand(@Param("brand") String brand);

    @Query(value = "Select a.* from advert as a "
            + "inner join vehicle as v on a.vehicle_id = v.id "
            + "inner join category as c on v.category_id = c.id "
            + "where c.category_name =:category", nativeQuery = true)
    List<Advert> getAllAdvertsByCategoryResponse(@Param("category") String category);

    @Query(value = "Select a.* from advert as a "
            + "inner join user as u on u.id = a.user_id "
            + "where u.id =:userId", nativeQuery = true)
    List<Advert> findAdvertsByUserId(@Param("userId") Long id);
}
