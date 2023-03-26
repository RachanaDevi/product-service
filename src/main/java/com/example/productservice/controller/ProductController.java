package com.example.productservice.controller;

import com.example.productservice.entity.Product;
import com.example.productservice.entity.ProductCategory;
import com.example.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/product/{id}")
    public ResponseEntity<com.example.productservice.response.Product> fetchProductById(@PathVariable Long id) {
        Product product = this.productService.findProductBy(id);
        return ResponseEntity.ok(product.toProductResponse());
    }

    // show all categories of that particular category
    // see if we want this or not
    // or use another path
    // had to change the path
    @GetMapping(value = "/products/categoryName/{category}")
    public ResponseEntity<List<com.example.productservice.response.Product>> fetchProductsByCategory(@PathVariable String category) {
        List<Product> products = this.productService.productsHavingCategory(category); // handle case sensitivity
        return ResponseEntity.ok(products.stream().map(Product::toProductResponse).collect(Collectors.toList()));
    }

    // show all categories of that particular category id
    @GetMapping(value = "/products/category/{categoryId}")
    public ResponseEntity<List<com.example.productservice.response.Product>> fetchProductsByCategoryId(@PathVariable Long categoryId) {
        List<Product> products = this.productService.productsHavingCategory(categoryId); // handle case sensitivity
        return ResponseEntity.ok(products.stream().map(Product::toProductResponse).collect(Collectors.toList()));
    }

    // get all categories for drop down list
    @GetMapping(value = "/products/categories")
    public ResponseEntity<List<com.example.productservice.response.ProductCategory>> getAllCategories() {
        List<ProductCategory> productCategories = this.productService.allProductCategories();
        return ResponseEntity.ok(productCategories.stream().map(ProductCategory::toProductCategoryResponse).collect(Collectors.toList()));
    }
}
