package com.sysops_squad.productservice.controller;

import com.sysops_squad.productservice.model.Product;
import com.sysops_squad.productservice.model.ProductCategory;
import com.sysops_squad.productservice.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/categories")
    @ResponseBody
    public List<ProductCategory> allProductCategories() {
        return productService.allProductCategories().stream()
                .map(com.sysops_squad.productservice.entity.ProductCategory::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/products/categoryName/{categoryName}")
    @ResponseBody
    public List<Product> allProductCategories(@PathVariable String categoryName) {
        return productService.productsHavingCategory(categoryName).stream()
                .map(com.sysops_squad.productservice.entity.Product::toResponse)
                .collect(Collectors.toList());
    }
}
