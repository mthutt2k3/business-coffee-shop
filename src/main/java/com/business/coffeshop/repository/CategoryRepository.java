package com.business.coffeshop.repository;

import com.business.coffeshop.dto.CategoryDto;
import com.business.coffeshop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT new com.business.coffeshop.dto.CategoryDto(" +
            "c.id, c.categoryName, c.description, c.imageUrl, CAST(COUNT(p.id) AS string)) " +
            "FROM Category c " +
            "LEFT JOIN Product p ON c.id = p.category.id " +  // Truy vấn trực tiếp qua khóa ngoại
            "WHERE c.deleted = false " +
            "AND p.deleted = false " +
            "GROUP BY c.id, c.categoryName, c.description, c.imageUrl")
    List<CategoryDto> findAllCustomerProductCategories();
}
