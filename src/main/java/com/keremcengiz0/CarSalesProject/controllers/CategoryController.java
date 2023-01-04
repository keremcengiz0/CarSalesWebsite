package com.keremcengiz0.CarSalesProject.controllers;


import com.keremcengiz0.CarSalesProject.dtos.AdvertDto;
import com.keremcengiz0.CarSalesProject.dtos.CategoryDto;
import com.keremcengiz0.CarSalesProject.requests.AdvertCreateRequest;
import com.keremcengiz0.CarSalesProject.requests.AdvertUpdateRequest;
import com.keremcengiz0.CarSalesProject.requests.CategoryCreateRequest;
import com.keremcengiz0.CarSalesProject.requests.CategoryUpdateRequest;
import com.keremcengiz0.CarSalesProject.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public CategoryDto createOneCategory(@RequestBody CategoryCreateRequest newCategoryRequest) {
        return this.categoryService.createOneCategory(newCategoryRequest);
    }

    @PutMapping("/{categoryId}")
    public CategoryDto updateOneCategory(@PathVariable(name = "categoryId") Long id, @RequestBody CategoryUpdateRequest updateRequest) throws Exception {
        return this.categoryService.updateOneAdvert(id, updateRequest);
    }

    @DeleteMapping("/{categoryId}")
    public void CategoryDto(@PathVariable(name = "categoryId") Long id) throws Exception {
        this.categoryService.deleteOneCategoryById(id);
    }


}
