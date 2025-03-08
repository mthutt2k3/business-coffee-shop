package com.business.coffeshop.service.impl;

import com.business.coffeshop.dto.MarketerProductDto;
import com.business.coffeshop.dto.ProductDto;
import com.business.coffeshop.entity.Product;
import com.business.coffeshop.mapper.ProductMapper;
import com.business.coffeshop.repository.ProductRepository;
import com.business.coffeshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            return Collections.emptyList();
        }
        List<ProductDto> productDtos = toProductDtos(products);
        return productDtos;
    }


    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return null;
        }
        return productMapper.toProductDto(product);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct == null) {
            return null;
        }
        existingProduct.setProductName(product.getProductName());
        existingProduct.setOriginalPrice(product.getOriginalPrice());
        existingProduct.setSellingPrice(product.getSellingPrice());
        existingProduct.setDescription(product.getDescription());
        return productRepository.save(existingProduct);
    }

    @Override
    public List<MarketerProductDto> getAllMarketerProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            return Collections.emptyList();
        }
        return toMarketerProductDtos(products);
    }

    @Override
    public MarketerProductDto getMarketerProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return null;
        }
        return productMapper.toMarketerProductDto(product);
    }

    private List<MarketerProductDto> toMarketerProductDtos(List<Product> products) {
        return products.stream()
                .map(productMapper::toMarketerProductDto)
                .collect(Collectors.toList());
    }

    private List<ProductDto> toProductDtos(List<Product> products) {
        return products.stream()
                .map(productMapper::toProductDto)
                .collect(Collectors.toList());
    }
    private List<Product> toProductEntities(List<ProductDto> products) {
        return products.stream()
                .map(productMapper::toProductEntity)
                .collect(Collectors.toList());
    }

}
