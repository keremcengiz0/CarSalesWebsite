package com.keremcengiz0.CarSalesProject.responses;

import com.keremcengiz0.CarSalesProject.dtos.AdvertDto;
import lombok.Data;
import java.util.List;

@Data
public class GetAllAdvertsByBrandResponse {
    List<AdvertDto> adverts;

}