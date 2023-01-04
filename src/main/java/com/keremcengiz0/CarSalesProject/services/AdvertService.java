package com.keremcengiz0.CarSalesProject.services;


import com.keremcengiz0.CarSalesProject.dtos.AdvertDto;
import com.keremcengiz0.CarSalesProject.entities.Advert;
import com.keremcengiz0.CarSalesProject.requests.AdvertCreateRequest;
import com.keremcengiz0.CarSalesProject.requests.AdvertUpdateRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdvertService {
    List<AdvertDto> getAllAdverts();
    void deleteOneAdvertById(Long id) throws Exception;
    AdvertDto getOneAdvertById(Long id) throws Exception;
    List<AdvertDto> getAllAdvertsByBrand(String brand);
    AdvertDto createOneAdvert(AdvertCreateRequest newAdvertRequest) throws Exception;
    AdvertDto updateOneAdvert(Long id, AdvertUpdateRequest updateRequest) throws Exception;
}
