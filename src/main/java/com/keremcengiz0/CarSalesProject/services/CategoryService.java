package com.keremcengiz0.CarSalesProject.services;


import com.keremcengiz0.CarSalesProject.dtos.CategoryDto;
import com.keremcengiz0.CarSalesProject.requests.CategoryCreateRequest;
import com.keremcengiz0.CarSalesProject.requests.CategoryUpdateRequest;

public interface CategoryService {
    CategoryDto createOneCategory(CategoryCreateRequest newCategoryRequest);
    CategoryDto updateOneAdvert(Long id, CategoryUpdateRequest updateRequest) throws Exception;
    void deleteOneCategoryById(Long id) throws Exception;
}
