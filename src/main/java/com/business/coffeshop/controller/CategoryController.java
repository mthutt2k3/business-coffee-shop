package com.business.coffeshop.controller;

import com.business.coffeshop.entity.Category;
import com.business.coffeshop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/${application-context-name}/private/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showCategoryList(Model model) {
        List<Category> categories = categoryService.getAllProductCategories();
        model.addAttribute("categories", categories);
        return "category-list"; // Trả về giao diện category-list.html
    }
}
