package com.sysops_squad.productservice.controller;

import com.sysops_squad.productservice.model.ProductCategory;
import com.sysops_squad.productservice.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
}
