package com.example.productservice.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ProductCategory {

    private Long id;

    private String name;

    public ProductCategory(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
