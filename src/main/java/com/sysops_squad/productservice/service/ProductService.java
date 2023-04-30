package com.sysops_squad.productservice.service;

import com.sysops_squad.productservice.entity.Product;
import com.sysops_squad.productservice.entity.ProductCategory;
import com.sysops_squad.productservice.repository.ProductCategoryRepository;
import com.sysops_squad.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRepository productRepository;

    public ProductService(ProductCategoryRepository productCategoryRepository, ProductRepository productRepository) {
        this.productCategoryRepository = productCategoryRepository;
        this.productRepository = productRepository;
    }

    public List<ProductCategory> allProductCategories() {
        return productCategoryRepository.findAll();
    }

    public List<Product> productsHavingCategory(String categoryName) {
        return productRepository.findByCategoryName(categoryName);
    }
}
