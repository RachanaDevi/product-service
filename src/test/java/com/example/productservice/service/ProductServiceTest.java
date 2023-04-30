package com.example.productservice.service;

import com.example.productservice.entity.ProductCategory;
import com.example.productservice.repository.ProductCategoryRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    @Test
    void shouldReturnAllProductCategories() {
        ProductCategoryRepository productCategoryRepository = mock(ProductCategoryRepository.class);
        when(productCategoryRepository.findAll()).thenReturn(List.of(anyProductCategory()));

        ProductService productService = new ProductService(productCategoryRepository);

        assertThat(productService.allProductCategories()).containsExactly(anyProductCategory());
    }

    private ProductCategory anyProductCategory() {
        return new com.example.productservice.entity.ProductCategory(1L, "TELEVISION");
    }
}