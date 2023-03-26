package com.example.productservice.service;

import com.example.productservice.entity.Product;
import com.example.productservice.entity.ProductCategory;
import com.example.productservice.exception.ProductNotFoundException;
import com.example.productservice.repository.ProductCategoryRepository;
import com.example.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    private final ProductCategoryRepository productCategoryRepository;

    public ProductService(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
    }

    public Product findProductBy(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
    }

    public List<Product> productsHavingCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> productsHavingCategory(Long categoryId) {
        return productRepository.findByCategory(categoryId);
    }

    public List<ProductCategory> allProductCategories() {
        return productCategoryRepository.findAll();
    }
}
