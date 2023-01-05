package com.keremcengiz0.CarSalesProject.controllers;

import com.keremcengiz0.CarSalesProject.dtos.AdvertDto;
import com.keremcengiz0.CarSalesProject.requests.AdvertCreateRequest;
import com.keremcengiz0.CarSalesProject.requests.AdvertUpdateRequest;
import com.keremcengiz0.CarSalesProject.responses.GetAllAdvertsByBrandResponse;
import com.keremcengiz0.CarSalesProject.responses.GetAllAdvertsByCategoryResponse;
import com.keremcengiz0.CarSalesProject.responses.GetAllAdvertsResponse;
import com.keremcengiz0.CarSalesProject.responses.GetOneAdvertResponse;
import com.keremcengiz0.CarSalesProject.services.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adverts")
public class AdvertController {

    private AdvertService advertService;

    @Autowired
    public AdvertController(AdvertService advertService) {
        this.advertService = advertService;
    }

    @GetMapping("")
    public GetAllAdvertsResponse getAllAdvertsResponse() {
        List<AdvertDto> adverts = advertService.getAllAdverts();
        GetAllAdvertsResponse advertResponse = new GetAllAdvertsResponse();
        advertResponse.setAdverts(adverts);
        return advertResponse;
    }

    @GetMapping("/{advertId}")
    public GetOneAdvertResponse getOneAdvertResponse(@PathVariable(name = "advertId") Long id) throws Exception {
        AdvertDto advertDto = this.advertService.getOneAdvertById(id);
        GetOneAdvertResponse getOneAdvertResponse = new GetOneAdvertResponse();
        getOneAdvertResponse.setAdvertDto(advertDto);
        return getOneAdvertResponse;
    }

    @GetMapping("/brand/{brand}")
    public GetAllAdvertsByBrandResponse getAllAdvertsByBrandResponse(@PathVariable(value = "brand") String brand) throws Exception {
        List<AdvertDto> advertDto = this.advertService.getAllAdvertsByBrand(brand);
        GetAllAdvertsByBrandResponse getAllAdvertsByBrandResponse = new GetAllAdvertsByBrandResponse();
        getAllAdvertsByBrandResponse.setAdverts(advertDto);
        return getAllAdvertsByBrandResponse;
    }

    @GetMapping("/kategori/{category}")
    public GetAllAdvertsByCategoryResponse getAllAdvertsByCategoryResponse(@PathVariable(value = "category") String category) throws Exception {
        List<AdvertDto> advertDto = this.advertService.getAllAdvertsByCategoryResponse(category);
        GetAllAdvertsByCategoryResponse getAllAdvertsByCategoryResponse = new GetAllAdvertsByCategoryResponse();
        getAllAdvertsByCategoryResponse.setAdverts(advertDto);
        return getAllAdvertsByCategoryResponse;
    }



    @PostMapping
    public AdvertDto createOneAdvert(@RequestBody AdvertCreateRequest newAdvertRequest) throws Exception {
        return this.advertService.createOneAdvert(newAdvertRequest);
    }

    @PutMapping("/{advertId}")
    public AdvertDto updateOneAdvert(@PathVariable(name = "advertId") Long id, @RequestBody AdvertUpdateRequest updateRequest) throws Exception {
        return this.advertService.updateOneAdvert(id, updateRequest);
    }

    @DeleteMapping("/{advertId}")
    public void deleteOneAdvert(@PathVariable(name = "advertId") Long id) throws Exception {
        this.advertService.deleteOneAdvertById(id);
    }
}
