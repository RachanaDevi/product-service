package com.example.productservice.service;

import com.example.productservice.entity.ProductCategory;
import com.example.productservice.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductCategoryRepository productCategoryRepository;

    public ProductService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    public List<ProductCategory> allProductCategories() {
        return productCategoryRepository.findAll();
    }
}
