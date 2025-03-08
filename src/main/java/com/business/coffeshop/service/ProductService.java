package com.business.coffeshop.service;

import com.business.coffeshop.dto.AddProductRequest;
import com.business.coffeshop.dto.MarketerProductDto;
import com.business.coffeshop.dto.ProductDto;
import com.business.coffeshop.entity.Product;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();

    ProductDto getProductById(Long id);

    Product createProduct(Product product);

    Product updateProduct(Long id, Product product);

    List<MarketerProductDto>  getAllMarketerProducts();

    MarketerProductDto getMarketerProductById(Long id);

    Product saveProduct(AddProductRequest product);
}
