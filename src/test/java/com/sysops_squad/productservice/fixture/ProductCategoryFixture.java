package com.sysops_squad.productservice.fixture;

import com.sysops_squad.productservice.entity.ProductCategory;

public class ProductCategoryFixture {

    public static class Entity {

        public static ProductCategory anyProductCategory() {
            return new ProductCategory(1L, anyTelevisionCategory());
        }

        public static ProductCategory anyProductCategoryWith(String categoryName) {
            return new ProductCategory(1L, categoryName);
        }

        public static ProductCategory anyProductCategoryWith(Long categoryId) {
            return new ProductCategory(categoryId, anyTelevisionCategory());
        }
    }

    public static class Model {

        public static com.sysops_squad.productservice.model.ProductCategory anyProductCategory() {
            return new com.sysops_squad.productservice.model.ProductCategory(1L, anyTelevisionCategory());
        }
    }

    public static String anyTelevisionCategory() {
        return "TELEVISION";
    }
}
