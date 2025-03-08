package com.business.coffeshop.controller;

import com.business.coffeshop.dto.AddProductRequest;
import com.business.coffeshop.entity.Product;
import com.business.coffeshop.service.CategoryService;
import com.business.coffeshop.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("business-coffeeshop/product")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    //===================================FOR CUSTOMER========================================
    @GetMapping("customer-product-list")
    public String showProductList(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("categories", categoryService.getAllCustomerProductCategories());
        return "product/customer/customer-product-list";  // Trả về trang danh sách sản phẩm
    }
    @GetMapping("/customer-product-detail/{id}")
    public String getProductDetail(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("categories", categoryService.getAllCustomerProductCategories());
        return "product/customer/customer-product-detail";  // Trả về trang chi tiết sản phẩm
    }

    //===================================FOR BACKOFFICE========================================

    @GetMapping("marketer-product-list")
    public String showMarketerProductList(Model model) {
        model.addAttribute("products", productService.getAllMarketerProducts());
        return "product/marketer/marketer-product-list";  // Trả về trang danh sách sản phẩm
    }

    @GetMapping("marketer-product-detail/{id}")
    public String showMarketerProductDetail(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getMarketerProductById(id));
        return "product/marketer/marketer-product-detail";  // Trả về trang danh sách sản phẩm
    }
    @GetMapping("marketer-product-edit/{id}")
    public String showMarketerProductEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getMarketerProductById(id));
        model.addAttribute("categories", categoryService.getAllProductCategories());
        return "product/marketer/marketer-update-product";  // Trả về trang danh sách sản phẩm
    }
    @GetMapping("marketer-product-add")
    public String showMarketerProductAddForm(Model model) {
        model.addAttribute("product", new AddProductRequest());
        model.addAttribute("categories", categoryService.getAllProductCategories());
        return "product/marketer/marketer-add-product";  // Trả về trang danh sách sản phẩm
    }

    @PostMapping("marketer-product-add")
    public String addProduct(@Valid @ModelAttribute("product") AddProductRequest product, BindingResult result, Model model) {
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllProductCategories());

        // Nếu có lỗi validation thì quay lại form nhập liệu
        if (result.hasErrors()) {
            return "product/marketer/marketer-add-product";
        }
        try {
            productService.saveProduct(product);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An unexpected error occurred: " + e.getMessage());
        }
        return "product/marketer/marketer-add-product";
    }

}
