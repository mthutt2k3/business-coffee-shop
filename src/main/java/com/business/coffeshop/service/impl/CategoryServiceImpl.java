package com.business.coffeshop.service.impl;

import com.business.coffeshop.dto.CategoryDto;
import com.business.coffeshop.entity.Category;
import com.business.coffeshop.repository.CategoryRepository;
import com.business.coffeshop.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllProductCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<CategoryDto> getAllCustomerProductCategories() {
        List<CategoryDto> categoryDtos = categoryRepository.findAllCustomerProductCategories();
        return categoryDtos;
    }
}
