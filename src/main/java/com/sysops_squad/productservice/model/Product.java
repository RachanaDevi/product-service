package com.sysops_squad.productservice.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(category, product.category) && Objects.equals(name, product.name) && Objects.equals(brand, product.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, name, brand);
    }
}