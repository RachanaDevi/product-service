package com.example.productservice.service;

import com.example.productservice.model.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    public List<ProductCategory> allProductCategories() {
        return List.of(new ProductCategory(1l, "TELEVISION"));
    }
}
