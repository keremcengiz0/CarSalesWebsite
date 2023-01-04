package com.keremcengiz0.CarSalesProject.services;

import com.keremcengiz0.CarSalesProject.dtos.CategoryDto;
import com.keremcengiz0.CarSalesProject.entities.Category;
import com.keremcengiz0.CarSalesProject.repositories.CategoryRepository;
import com.keremcengiz0.CarSalesProject.requests.CategoryCreateRequest;
import com.keremcengiz0.CarSalesProject.requests.CategoryUpdateRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto createOneCategory(CategoryCreateRequest newCategoryRequest) {

        CategoryDto toSaveCategoryDto = new CategoryDto();
        toSaveCategoryDto.setCategoryName(newCategoryRequest.getCategoryName());

        Category category = this.modelMapper.map(toSaveCategoryDto, Category.class);
        this.categoryRepository.save(category);
        toSaveCategoryDto.setId(category.getId());

        return toSaveCategoryDto;
    }

    @Override
    public CategoryDto updateOneAdvert(Long id, CategoryUpdateRequest updateRequest) throws Exception {

        Optional<Category> category = this.categoryRepository.findById(id);

        if(category.isEmpty()) {
            throw new Exception("No category found to update!");
        }

        Category toUpdateCategory = category.get();
        toUpdateCategory.setCategoryName(updateRequest.getCategoryName());

        CategoryDto updatedCategory = this.modelMapper.map(toUpdateCategory, CategoryDto.class);
        this.categoryRepository.save(toUpdateCategory);
        updatedCategory.setId(toUpdateCategory.getId());

        return updatedCategory;
    }

    @Override
    public void deleteOneCategoryById(Long id) throws Exception {

        Optional<Category> category = this.categoryRepository.findById(id);

        if(category.isEmpty()) {
            throw new Exception("Category not found to delete!");
        }

        Category foundCategoryToDelete = category.get();
        this.categoryRepository.deleteById(foundCategoryToDelete.getId());

    }
}
