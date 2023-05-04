package com.sysops_squad.productservice.fixture;

import com.sysops_squad.productservice.entity.Product;

import static com.sysops_squad.productservice.fixture.ProductCategoryFixture.Entity.anyProductCategory;
import static com.sysops_squad.productservice.fixture.ProductCategoryFixture.Entity.anyProductCategoryWith;

public class ProductFixture {


    public static class Entity {
        public static Product anyTelevisionProductWithProductCategoryId(Long productCategoryId) {
            return new Product(1L, 1L, "LG Television", "LG", "LG", anyProductCategoryWith(productCategoryId));
        }

        public static Product anyTelevisionProductWithProductCategoryName(String categoryName) {
            return new Product(1L, 1L, "LG Television", "LG", "LG", anyProductCategoryWith(categoryName));
        }

        public static Product anyTelevisionProduct() {
            return new Product(1L, 1L, "LG Television", "LG", "LG", anyProductCategory());
        }

        public static Product anyOtherTelevisionProduct() {
            return new Product(2L, 1L, "Samsung Television", "Samsung", "Samsung", anyProductCategory());
        }
    }

    public static class Model {
        public static com.sysops_squad.productservice.model.Product anyTelevisionProductWithProductCategoryName(String categoryName) {
            return new com.sysops_squad.productservice.model.Product(1L, categoryName,
                    "LG Television", "LG");
        }

        public static com.sysops_squad.productservice.model.Product anyTelevisionProduct() {
            return new com.sysops_squad.productservice.model.Product(1L, "TELEVISION",
                    "LG Television", "LG");
        }

        public static com.sysops_squad.productservice.model.Product anyTelevisionProductWithProductCategoryId(Long productCategoryId) {
            return new com.sysops_squad.productservice.model.Product(productCategoryId, "TELEVISION",
                    "LG Television", "LG");
        }
    }
}
