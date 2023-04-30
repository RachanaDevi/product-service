package com.example.productservice.controller;

import com.example.productservice.model.ProductCategory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProductController {

    @GetMapping("/productCategories")
    @ResponseBody
    public List<ProductCategory> allProductCategories() {
        return List.of(new ProductCategory(1l, "TELEVISION"));
    }
}
