package com.sysops_squad.productservice.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {

    @Test
    void shouldEquateTwoProducts() {
        Product product = new Product(1L, "TELEVISION", "LG Television", "LG");
        Product otherProduct = new Product(1L, "TELEVISION", "LG Television", "LG");

        assertThat(product).isEqualTo(otherProduct);
    }

    @Test
    void shouldNotEquateTwoProducts() {
        Product product = new Product(1L, "TELEVISION", "LG Television", "LG");
        Product otherProduct = new Product(2L, "TELEVISION", "LG Television", "LG");

        assertThat(product).isNotEqualTo(otherProduct);
    }
}