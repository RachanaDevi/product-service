package com.sysops_squad.productservice.repository;

import com.sysops_squad.productservice.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract class ProductCategoryRepository implements JpaRepository<ProductCategory, Long> {
}
