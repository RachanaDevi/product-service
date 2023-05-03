package com.sysops_squad.productservice.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductNotFoundExceptionTest {

    @Test
    void shouldReturnExceptionMessage() {
        long anyProductId = 2L;
        ProductNotFoundException productNotFoundException = new ProductNotFoundException(anyProductId);

        assertThat(productNotFoundException.getMessage()).isEqualTo("Product with id:2 not found");
    }
}