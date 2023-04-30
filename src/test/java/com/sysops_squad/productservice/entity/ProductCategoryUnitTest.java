package com.sysops_squad.productservice.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductCategoryUnitTest {

    @Test
    void shouldReturnProductCategoryResponse() {
        ProductCategory productCategory = new ProductCategory(anyId(), anyProductCategoryName());

        com.sysops_squad.productservice.model.ProductCategory productCategoryResponse = new com.sysops_squad.productservice.model.ProductCategory(anyId(), anyProductCategoryName());

        assertThat(productCategory.toResponse()).isEqualTo(productCategoryResponse);
    }

    @Test
    void shouldEquateProductCategories() {
        ProductCategory productCategory = new ProductCategory(anyId(), anyProductCategoryName());
        ProductCategory otherProductCategory = new ProductCategory(anyId(), anyProductCategoryName());

        assertThat(productCategory).isEqualTo(otherProductCategory);
    }

    @Test
    void shouldNotEquateProductCategories() {
        ProductCategory productCategory = new ProductCategory(anyId(), anyProductCategoryName());
        ProductCategory otherProductCategory = new ProductCategory(anyOtherId(), anyProductCategoryName());

        assertThat(productCategory).isNotEqualTo(otherProductCategory);
    }

    private String anyProductCategoryName() {
        return "TELEVISION";
    }

    private long anyId() {
        return 1L;
    }

    private long anyOtherId() {
        return 2L;
    }

}