package com.business.coffeshop.service;

import com.business.coffeshop.dto.CategoryDto;
import com.business.coffeshop.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllProductCategories();
    List<CategoryDto> getAllCustomerProductCategories();
}
