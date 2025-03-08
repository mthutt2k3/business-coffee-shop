package com.business.coffeshop.service.impl;

import com.business.coffeshop.constant.CommonStatusEnum;
import com.business.coffeshop.dto.AddProductRequest;
import com.business.coffeshop.dto.MarketerProductDto;
import com.business.coffeshop.dto.ProductDto;
import com.business.coffeshop.entity.Category;
import com.business.coffeshop.entity.Product;
import com.business.coffeshop.mapper.ProductMapper;
import com.business.coffeshop.repository.ProductRepository;
import com.business.coffeshop.service.CategoryService;
import com.business.coffeshop.service.ProductService;
import com.business.coffeshop.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

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

    @Override
    public Product saveProduct(AddProductRequest addProductRequest) {
        validateProduct(addProductRequest);
        String imageUrl = FileUtils.saveFile(addProductRequest.getImageFile(), addProductRequest.getProductName());
        Category category = categoryService.getCategoryActiveById(addProductRequest.getCategoryId());
        Product product = getProductEntity(addProductRequest, imageUrl, category);
        return productRepository.save(product);
    }
    private void validateProduct(AddProductRequest product) {
        // 1️⃣ Kiểm tra giá bán hợp lệ
        if (product.getOriginalPrice() < product.getSellingPrice()) {
            throw new IllegalArgumentException("Selling price cannot be lower than original price.");
        }

        // 2️⃣ Kiểm tra file ảnh hợp lệ
        validateImageFile(product.getImageFile());

        // 3️⃣ Kiểm tra tên sản phẩm đã tồn tại chưa
        Optional<Product> existingProduct = productRepository.findByProductName(product.getProductName());
        if (existingProduct.isPresent()) {
            throw new IllegalArgumentException("Product name already exists.");
        }
    }
    private void validateImageFile(MultipartFile file) {
        // Danh sách định dạng ảnh hợp lệ
        List<String> allowedExtensions = Arrays.asList("jpg", "png");

        if (file.isEmpty()) {
            throw new IllegalArgumentException("Product image is required.");
        }

        // Lấy phần mở rộng của file
        String fileExtension = FileUtils.getFileExtension(file.getOriginalFilename());
        if (!allowedExtensions.contains(fileExtension.toLowerCase())) {
            throw new IllegalArgumentException("Only JPG and PNG images are allowed.");
        }
    }

    private Product getProductEntity(AddProductRequest addProductRequest, String imageUrl, Category category) {
        Product product = new Product();
        product.setProductName(addProductRequest.getProductName());
        product.setOriginalPrice(addProductRequest.getOriginalPrice());
        product.setSellingPrice(addProductRequest.getSellingPrice());
        product.setDescription(addProductRequest.getDescription());
        product.setCategory(category);
        product.setImageUrl(imageUrl);
        return product;
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
