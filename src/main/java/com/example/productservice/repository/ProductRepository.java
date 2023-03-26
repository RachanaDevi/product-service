package com.example.productservice.repository;

import com.example.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT productCategory.products from ProductCategory productCategory where productCategory.name=:category")
    List<Product> findByCategory(String category);

    @Query("SELECT productCategory.products from ProductCategory productCategory where productCategory.id=:categoryId")
    List<Product> findByCategory(Long categoryId);

}
