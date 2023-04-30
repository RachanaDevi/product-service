package com.example.productservice.controller;

import com.example.productservice.model.ProductCategory;
import com.example.productservice.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/productCategories")
    @ResponseBody
    public List<ProductCategory> allProductCategories() {
        return productService.allProductCategories();
    }
}
