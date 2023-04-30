package com.example.productservice.repository;

import com.example.productservice.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract class ProductCategoryRepository implements JpaRepository<ProductCategory, Long> {
}
