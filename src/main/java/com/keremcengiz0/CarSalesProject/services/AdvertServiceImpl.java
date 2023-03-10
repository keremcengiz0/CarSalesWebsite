package com.keremcengiz0.CarSalesProject.services;

import com.keremcengiz0.CarSalesProject.dtos.AdvertDto;
import com.keremcengiz0.CarSalesProject.dtos.UserDto;
import com.keremcengiz0.CarSalesProject.entities.Advert;
import com.keremcengiz0.CarSalesProject.entities.Image;
import com.keremcengiz0.CarSalesProject.entities.User;
import com.keremcengiz0.CarSalesProject.entities.Vehicle;
import com.keremcengiz0.CarSalesProject.repositories.AdvertRepository;
import com.keremcengiz0.CarSalesProject.repositories.ImageRepository;
import com.keremcengiz0.CarSalesProject.requests.AdvertCreateRequest;
import com.keremcengiz0.CarSalesProject.requests.AdvertUpdateRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdvertServiceImpl implements AdvertService {

    private UserService userService;
    private AdvertRepository advertRepository;
    private ModelMapper modelMapper;
    private ImageRepository imageRepository;

    @Autowired
    public AdvertServiceImpl(UserService userService, AdvertRepository advertRepository,ImageRepository imageRepository, ModelMapper modelMapper) {
        this.userService = userService;
        this.advertRepository = advertRepository;
        this.imageRepository = imageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AdvertDto> getAllAdverts() {
        return this.advertRepository.findAll().stream().map(advert -> modelMapper.map(advert, AdvertDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<AdvertDto> getAllAdvertsByBrand(String brand) throws Exception {
        List<Advert> adverts = this.advertRepository.getAllAdvertsByBrand(brand);

        if (adverts.isEmpty()) {
            throw new Exception("No vehicle found for this brand!");
        }

        return this.advertRepository.getAllAdvertsByBrand(brand).stream().map(advert -> modelMapper.map(advert, AdvertDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<AdvertDto> getAllAdvertsByCategoryResponse(String category) throws Exception {
        List<Advert> adverts = this.advertRepository.getAllAdvertsByCategoryResponse(category);

        if (adverts.isEmpty()) {
            throw new Exception("No vehicle found for this category! Or No such category found!");
        }

        return this.advertRepository.getAllAdvertsByCategoryResponse(category).stream().map(advert -> modelMapper.map(advert, AdvertDto.class)).collect(Collectors.toList());
    }


    @Override
    public void deleteOneAdvertById(Long id) throws Exception {
        Optional<Advert> advert = this.advertRepository.findById(id);

        if (advert.isEmpty()) {
            throw new Exception("Advert not found to delete!");
        }

        Advert foundAdvertToDelete = advert.get();
        this.advertRepository.deleteById(foundAdvertToDelete.getId());
    }

    @Override
    public AdvertDto getOneAdvertById(Long id) throws Exception {
        Optional<Advert> advertOptional = this.advertRepository.findById(id);

        if (advertOptional.isEmpty()) {
            throw new Exception("Advert not found!");
        }
        Advert advert = advertOptional.get();
        return this.modelMapper.map(advert, AdvertDto.class);
    }

    @Override
    public AdvertDto createOneAdvert(AdvertCreateRequest newAdvertRequest) throws Exception {
        UserDto userDto = this.userService.getOneUserById(newAdvertRequest.getUserId());

        LocalDate date = LocalDate.now();

        if (userDto == null) {
            throw new Exception("No user found to add advert.");
        }

        AdvertDto toSaveAdvertDto = new AdvertDto();
        toSaveAdvertDto.setAdvertDate(date);
        toSaveAdvertDto.setTitle(newAdvertRequest.getTitle());
        toSaveAdvertDto.setDescription(newAdvertRequest.getDescription());
        toSaveAdvertDto.setVehicle(newAdvertRequest.getVehicle());

        Advert advert = this.modelMapper.map(toSaveAdvertDto, Advert.class);

        User user = this.modelMapper.map(userDto, User.class);
        advert.setUser(user);

        this.advertRepository.save(advert);


        List<Image> images = new ArrayList<>();
        for (Image image : newAdvertRequest.getImages()) {
            Image img = new Image();
            img.setImageUrl(image.getImageUrl());
            img.setAdvert(advert);
            images.add(img);
        }
        this.imageRepository.saveAll(images);

        toSaveAdvertDto.setId(advert.getId());
        return toSaveAdvertDto;
    }

    @Override
    public AdvertDto updateOneAdvert(Long id, AdvertUpdateRequest updateRequest) throws Exception {
        Optional<Advert> advert = this.advertRepository.findById(id);
        Optional<Vehicle> vehicle = Optional.ofNullable(advert.get().getVehicle());

        if (advert.isEmpty()) {
            throw new Exception("No advert found to update!");
        }

        if (vehicle.isEmpty()) {
            throw new Exception("No vehicle found to update!");
        }

        Advert toUpdateAdvert = advert.get();
        toUpdateAdvert.setTitle(updateRequest.getTitle());
        toUpdateAdvert.setDescription(updateRequest.getDescription());

        Vehicle toUpdateVehicle = vehicle.get();
        toUpdateVehicle.setKm(updateRequest.getVehicle().getKm());
        toUpdateVehicle.setFuel(updateRequest.getVehicle().getFuel());
        toUpdateVehicle.setBrand(updateRequest.getVehicle().getBrand());
        toUpdateVehicle.setModel(updateRequest.getVehicle().getModel());
        toUpdateVehicle.setCategory(updateRequest.getVehicle().getCategory());
        toUpdateVehicle.setPrice(updateRequest.getVehicle().getPrice());
        toUpdateVehicle.setYear(updateRequest.getVehicle().getYear());
        toUpdateVehicle.setSeries(updateRequest.getVehicle().getSeries());
        toUpdateVehicle.setGearType(updateRequest.getVehicle().getGearType());

        toUpdateAdvert.setVehicle(toUpdateVehicle);

        AdvertDto updatedAdvert = this.modelMapper.map(toUpdateAdvert, AdvertDto.class);
        this.advertRepository.save(toUpdateAdvert);
        updatedAdvert.setId(toUpdateAdvert.getId());

        return updatedAdvert;
    }


}
