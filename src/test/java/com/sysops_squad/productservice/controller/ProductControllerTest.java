package com.sysops_squad.productservice.controller;


import com.sysops_squad.productservice.entity.Product;
import com.sysops_squad.productservice.entity.ProductCategory;
import com.sysops_squad.productservice.service.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void shouldReturnAllProductCategories() throws Exception {
        when(productService.allProductCategories()).thenReturn(List.of(televisionProductCategory()));

        mockMvc.perform(get("/products/categories")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> Assertions.assertThat(result.getResponse().getContentAsString())
                        .isEqualTo("[{\"id\":1,\"name\":\"TELEVISION\"}]"));
    }

    @Test
    void shouldReturnProductsHavingAGivenProductCategoryName() throws Exception {
        String productCategoryName = "TELEVISION";
        when(productService.productsHavingCategory(productCategoryName)).thenReturn(List.of(anyTelevisionProduct(), anyOtherTelevisionProduct()));

        mockMvc.perform(get("/products/categoryName/" + productCategoryName).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> Assertions.assertThat(result.getResponse().getContentAsString())
                        .isEqualTo("[" +
                                "{\"id\":1,\"category\":\"" + productCategoryName + "\",\"name\":\"LG Television\",\"brand\":\"LG\"}," +
                                "{\"id\":2,\"category\":\"" + productCategoryName + "\",\"name\":\"Samsung Television\",\"brand\":\"Samsung\"}" +
                                "]"));
    }

    private ProductCategory televisionProductCategory() {
        return new ProductCategory(1L, "TELEVISION");
    }

    private Product anyTelevisionProduct() {
        return new Product(1L, 1L, "LG Television", "LG", "LG", televisionProductCategory());
    }

    private Product anyOtherTelevisionProduct() {
        return new Product(2L, 1L, "Samsung Television", "Samsung", "Samsung", televisionProductCategory());
    }
}
