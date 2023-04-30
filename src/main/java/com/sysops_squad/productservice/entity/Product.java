package com.sysops_squad.productservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long categoryId;

    private String name;

    private String brand;

    private String manufacturer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private ProductCategory productCategory;

    public Product() {
    }

    public Product(Long categoryId, String name, String brand, String manufacturer) {
        this.categoryId = categoryId;
        this.name = name;
        this.brand = brand;
        this.manufacturer = manufacturer;
    }

    public Product(Long id, Long categoryId, String name, String brand, String manufacturer, ProductCategory productCategory) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.brand = brand;
        this.manufacturer = manufacturer;
        this.productCategory = productCategory;
    }

    public com.sysops_squad.productservice.model.Product toResponse() {
        return new com.sysops_squad.productservice.model.Product(id, productCategory.name(), name, brand);
    }
}
