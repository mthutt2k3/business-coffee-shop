package com.business.coffeshop.mapper;

import com.business.coffeshop.dto.AddProductRequest;
import com.business.coffeshop.dto.MarketerProductDto;
import com.business.coffeshop.dto.ProductDto;
import com.business.coffeshop.entity.Product;
import com.business.coffeshop.helper.StringHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;
@Mapper(componentModel = "spring")
public abstract class ProductMapper {

    // Convert ProductDto to Product Entity
    @Mappings({
            @Mapping(source = "accountId", target = "account.id"),
            @Mapping(source = "categoryName", target = "category.categoryName"),
            @Mapping(source = "imageUrls", target = "imageUrl", qualifiedByName = "convertListToString"),  // Assuming the field is stored as a string in DB
            @Mapping(source = "originalPrice", target = "originalPrice", qualifiedByName = "parseCurrency"),
            @Mapping(source = "sellingPrice", target = "sellingPrice", qualifiedByName = "parseCurrency")
    })
    public abstract Product toProductEntity(ProductDto productDto);

    // Convert Product Entity to ProductDto
    @Mappings({
            @Mapping(source = "account.id", target = "accountId"),
            @Mapping(source = "category.categoryName", target = "categoryName"),
            @Mapping(source = "imageUrl", target = "imageUrls", qualifiedByName = "splitImageUrl"),  // ImageUrl is a List in DTO, handle conversion if necessary
            @Mapping(source = "originalPrice", target = "originalPrice", qualifiedByName = "getCurrencyFormat"),
            @Mapping(source = "sellingPrice", target = "sellingPrice", qualifiedByName = "getCurrencyFormat"),
            @Mapping(target = "discountPercentage", expression = "java(calculateDiscountPercentage(product.getOriginalPrice(), product.getSellingPrice()))")

    })
    public abstract ProductDto toProductDto(Product product);

    @Mappings({
            @Mapping(source = "originalPrice", target = "originalPrice", qualifiedByName = "getCurrencyFormat"),
            @Mapping(source = "sellingPrice", target = "sellingPrice", qualifiedByName = "getCurrencyFormat")
    })
    public abstract MarketerProductDto toMarketerProductDto(Product product);


    // Convert List<String> to String (comma-separated)
    @Named("convertListToString")
    public String convertListToString(List<String> imageUrls) {
        return StringHelper.convertListToString(imageUrls);
    }

    @Named("splitImageUrl")
    public List<String> splitImageUrl(String imageUrl) {
        return StringHelper.convertStringToList(imageUrl);
    }

    @Named("getCurrencyFormat")
    public String getCurrencyFormat(double price) {
        return StringHelper.formatCurrency(price);
    }

    @Named("parseCurrency")
    public double parseCurrency(String price) {
        return StringHelper.parseCurrency(price);
    }

    @Named("calculateDiscountPercentage")
    public int calculateDiscountPercentage(double originalPrice, double sellingPrice) {
        if (originalPrice <= 0) {
            return 0; // Tránh chia cho 0
        }

        double discountPercentage = ((originalPrice - sellingPrice) / originalPrice) * 100;
        return (int) Math.round(discountPercentage); // Làm tròn số nguyên
    }

}
