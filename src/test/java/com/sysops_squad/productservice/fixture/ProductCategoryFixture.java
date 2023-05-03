package com.sysops_squad.productservice.fixture;

import com.sysops_squad.productservice.entity.ProductCategory;

public class ProductCategoryFixture {

    public static ProductCategory anyProductCategory() {
        return new ProductCategory(1L, anyTelevisionCategory());
    }

    public static ProductCategory anyProductCategoryWith(String categoryName) {
        return new ProductCategory(1L, categoryName);
    }

    public static String anyTelevisionCategory() {
        return "TELEVISION";
    }
}
