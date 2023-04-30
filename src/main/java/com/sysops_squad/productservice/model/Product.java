package com.sysops_squad.productservice.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Product {
    private Long id;
    private String category;
    private String name;
    private String brand;

    public Product(Long id, String category, String name, String brand) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.brand = brand;
    }
}