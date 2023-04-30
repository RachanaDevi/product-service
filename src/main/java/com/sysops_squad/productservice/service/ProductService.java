package com.sysops_squad.productservice.service;

import com.sysops_squad.productservice.entity.ProductCategory;
import com.sysops_squad.productservice.repository.ProductCategoryRepository;
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
