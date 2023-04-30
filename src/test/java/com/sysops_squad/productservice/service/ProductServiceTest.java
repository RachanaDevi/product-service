package com.sysops_squad.productservice.service;

import com.sysops_squad.productservice.entity.ProductCategory;
import com.sysops_squad.productservice.repository.ProductCategoryRepository;
import com.sysops_squad.productservice.repository.ProductRepository;
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

        ProductService productService = new ProductService(productCategoryRepository, mock(ProductRepository.class));

        assertThat(productService.allProductCategories()).containsExactly(anyProductCategory());
    }

    private ProductCategory anyProductCategory() {
        return new ProductCategory(1L, "TELEVISION");
    }
}