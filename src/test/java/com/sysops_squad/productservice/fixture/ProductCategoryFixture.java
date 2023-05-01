package com.sysops_squad.productservice.fixture;

import com.sysops_squad.productservice.entity.ProductCategory;

public class ProductCategoryFixture {

    public static ProductCategory anyProductCategory() {
        return new ProductCategory(1L, anyTelevisionCategory());
    }

    public static String anyTelevisionCategory() {
        return "TELEVISION";
    }
}
