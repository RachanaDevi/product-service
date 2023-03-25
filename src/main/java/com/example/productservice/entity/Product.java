package com.example.productservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "category_id", insertable = false, updatable = false)
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

    public com.example.productservice.response.Product toProductResponse() {
        return new com.example.productservice.response.Product(this.id, this.productCategory.name(), this.name, this.brand);
    }
}
