package com.business.coffeshop.service.impl;

import com.business.coffeshop.entity.Product;
import com.business.coffeshop.repository.ProductRepository;
import com.business.coffeshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = getProductById(id);
        existingProduct.setProductName(product.getProductName());
        existingProduct.setOriginalPrice(product.getOriginalPrice());
        existingProduct.setSellingPrice(product.getSellingPrice());
        existingProduct.setDescription(product.getDescription());
        return productRepository.save(existingProduct);
    }

}
