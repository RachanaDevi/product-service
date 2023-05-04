package com.sysops_squad.productservice.controller;

import com.sysops_squad.productservice.exception.ProductNotFoundException;
import com.sysops_squad.productservice.model.Product;
import com.sysops_squad.productservice.model.ProductCategory;
import com.sysops_squad.productservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

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
    public ResponseEntity<List<ProductCategory>> allProductCategories() {
        return ResponseEntity.ok(productService.allProductCategories().stream()
                .map(com.sysops_squad.productservice.entity.ProductCategory::toResponse)
                .collect(Collectors.toList()));
    }

    @GetMapping("/products/categoryName/{categoryName}")
    @ResponseBody
    public ResponseEntity<List<Product>> allProductCategories(@PathVariable String categoryName) {
        return ResponseEntity.ok(productService.productsHavingCategory(categoryName).stream()
                .map(com.sysops_squad.productservice.entity.Product::toResponse)
                .collect(Collectors.toList()));
    }

    @GetMapping("/product/{id}")
    @ResponseBody
    public ResponseEntity<?> productHavingId(@PathVariable Long id) {
        try {

            return ResponseEntity.ok(productService.findByProductId(id).toResponse());
        } catch (ProductNotFoundException productNotFoundException) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, productNotFoundException.getMessage(), productNotFoundException);
        }
    }

    @GetMapping("/products/category/{categoryId}")
    @ResponseBody
    public ResponseEntity<List<Product>> productsHavingCategoryId(@PathVariable Long categoryId) {
        return ResponseEntity.ok(productService.productsHavingCategoryId(categoryId).stream()
                .map(com.sysops_squad.productservice.entity.Product::toResponse)
                .collect(Collectors.toList()));
    }
}
