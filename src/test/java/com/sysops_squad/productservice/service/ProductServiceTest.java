package com.sysops_squad.productservice.service;

import com.sysops_squad.productservice.entity.Product;
import com.sysops_squad.productservice.exception.ProductNotFoundException;
import com.sysops_squad.productservice.repository.ProductCategoryRepository;
import com.sysops_squad.productservice.repository.ProductRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.sysops_squad.productservice.fixture.ProductCategoryFixture.anyProductCategory;
import static com.sysops_squad.productservice.fixture.ProductCategoryFixture.anyTelevisionCategory;
import static com.sysops_squad.productservice.fixture.ProductFixture.anyTelevisionProduct;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

    @Test
    void shouldReturnProductGivenProductId() {
        Long anyProductId = 1L;
        Product anyProduct = anyTelevisionProduct();

        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findById(anyProductId)).thenReturn(Optional.of(anyProduct));

        ProductService productService = new ProductService(mock(ProductCategoryRepository.class), productRepository);

        assertThat(productService.findByProductId(anyProductId)).isEqualTo(anyProduct);
    }

    @Test
    void shouldThrowProductNotFoundExceptionGivenProductIdDoesNotExist() {
        Long nonExistentProductId = 1L;
        Optional<Product> nonExistentProduct = Optional.empty();

        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findById(nonExistentProductId)).thenReturn(nonExistentProduct);

        ProductService productService = new ProductService(mock(ProductCategoryRepository.class), productRepository);

        assertThatThrownBy(() -> productService.findByProductId(nonExistentProductId)).isExactlyInstanceOf(ProductNotFoundException.class);
    }

    @Test
    void shouldReturnProductsGivenCategoryId() {
        Long categoryId = 1L;

        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findByCategoryId(categoryId)).thenReturn(List.of(anyTelevisionProduct()));

        ProductService productService = new ProductService(mock(ProductCategoryRepository.class), productRepository);

        assertThat(productService.productsHavingCategoryId(categoryId)).containsExactly(anyTelevisionProduct());
    }
}