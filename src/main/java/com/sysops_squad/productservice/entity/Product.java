package com.sysops_squad.productservice.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(categoryId, product.categoryId) && Objects.equals(name, product.name) && Objects.equals(brand, product.brand) && Objects.equals(manufacturer, product.manufacturer) && Objects.equals(productCategory, product.productCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryId, name, brand, manufacturer, productCategory);
    }
}
