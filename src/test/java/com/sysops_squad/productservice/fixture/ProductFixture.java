package com.sysops_squad.productservice.fixture;

import com.sysops_squad.productservice.entity.Product;

import static com.sysops_squad.productservice.fixture.ProductCategoryFixture.anyProductCategory;

public class ProductFixture {

    public static Product anyTelevisionProduct() {
        return new Product(1L, 1L, "LG Television", "LG", "LG", anyProductCategory());
    }

    public static Product anyOtherTelevisionProduct() {
        return new Product(2L, 1L, "Samsung Television", "Samsung", "Samsung", anyProductCategory());
    }
}
