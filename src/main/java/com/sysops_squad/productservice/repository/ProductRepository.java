package com.sysops_squad.productservice.repository;

import com.sysops_squad.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT productCategory.products from ProductCategory productCategory where productCategory.name=:categoryName")
    List<Product> findByCategoryName(String categoryName);

    @Query("SELECT productCategory.products from ProductCategory productCategory where productCategory.id=:categoryId")
    List<Product> findByCategoryId(Long categoryId);
}
