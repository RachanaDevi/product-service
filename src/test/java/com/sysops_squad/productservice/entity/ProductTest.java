package com.sysops_squad.productservice.entity;

import org.junit.jupiter.api.Test;

import static com.sysops_squad.productservice.fixture.ProductFixture.anyOtherTelevisionProduct;
import static com.sysops_squad.productservice.fixture.ProductFixture.anyTelevisionProduct;
import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {

    @Test
    void shouldEquateTwoProducts() {
        Product product = anyTelevisionProduct();
        Product otherProduct = anyTelevisionProduct();

        assertThat(product).isEqualTo(otherProduct);
    }

    @Test
    void shouldNotEquateTwoProducts() {
        Product product = anyTelevisionProduct();
        Product otherProduct = anyOtherTelevisionProduct();

        assertThat(product).isNotEqualTo(otherProduct);
    }

    @Test
    void shouldReturnProductResponse() {
        Product product = anyTelevisionProduct();

        assertThat(product.toResponse()).isEqualTo(new com.sysops_squad.productservice.model.Product(1L, "TELEVISION", "LG Television", "LG"));
    }
}