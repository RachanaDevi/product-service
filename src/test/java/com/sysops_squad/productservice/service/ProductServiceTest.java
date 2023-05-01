package com.sysops_squad.productservice.service;

import com.sysops_squad.productservice.repository.ProductCategoryRepository;
import com.sysops_squad.productservice.repository.ProductRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.sysops_squad.productservice.fixture.ProductCategoryFixture.anyProductCategory;
import static com.sysops_squad.productservice.fixture.ProductCategoryFixture.anyTelevisionCategory;
import static com.sysops_squad.productservice.fixture.ProductFixture.anyTelevisionProduct;
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

    @Test
    void shouldReturnProductsGivenCategoryName() {
        String categoryName = anyTelevisionCategory();

        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findByCategoryName(categoryName)).thenReturn(List.of(anyTelevisionProduct()));

        ProductService productService = new ProductService(mock(ProductCategoryRepository.class), productRepository);

        assertThat(productService.productsHavingCategory(categoryName)).containsExactly(anyTelevisionProduct());
    }
}