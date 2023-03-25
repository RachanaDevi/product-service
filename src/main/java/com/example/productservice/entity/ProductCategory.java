package com.example.productservice.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_categories")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "productCategory")
    private List<Product> products = new ArrayList<>();

    public ProductCategory() {
    }

    public ProductCategory(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String name() {
        return name;
    }

    public com.example.productservice.response.ProductCategory toProductCategoryResponse() {
        return new com.example.productservice.response.ProductCategory(this.id, this.name);
    }
}
