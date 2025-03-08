package com.business.coffeshop.controller;

import com.business.coffeshop.service.CategoryService;
import com.business.coffeshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showProductList(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("categories", categoryService.getAllCustomerProductCategories());
        return "product/customer/customer-product-list";  // Trả về trang danh sách sản phẩm
    }

    @GetMapping("marketer")
    public String showMarketerProductList(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("categories", categoryService.getAllCustomerProductCategories());
        return "product/marketer/marketer-add-product";  // Trả về trang danh sách sản phẩm
    }


    @GetMapping("/customer/customer-product-list/{id}")
    public String getProductDetail(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("categories", categoryService.getAllCustomerProductCategories());
        return "product/customer/customer-product-detail";  // Trả về trang chi tiết sản phẩm
    }
//
//    @GetMapping("/create")
//    public String showCreateForm(Model model) {
//        model.addAttribute("product", new Product());
//        return "product/product-form";  // Trả về trang tạo sản phẩm
//    }
//
//    @PostMapping("/create")
//    public String createProduct(@ModelAttribute Product product) {
//        productService.createProduct(product);
//        return "redirect:/${application-context-name}/private/v1/product";  // Chuyển hướng về danh sách sản phẩm
//    }
//
//    @GetMapping("/update/{id}")
//    public String showUpdateForm(@PathVariable Long id, Model model) {
//        model.addAttribute("product", productService.getProductById(id));
//        return "product/product-form";  // Trả về trang cập nhật (dùng chung với create)
//    }
//
//    @PostMapping("/update")
//    public String updateProduct(Long id, @ModelAttribute Product product) {
//        productService.updateProduct(id, product);
//        return "redirect:/${application-context-name}/private/v1/product";
//    }

}
