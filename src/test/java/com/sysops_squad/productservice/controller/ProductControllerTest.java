package com.sysops_squad.productservice.controller;


import com.sysops_squad.productservice.exception.ProductNotFoundException;
import com.sysops_squad.productservice.service.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.sysops_squad.productservice.fixture.ProductCategoryFixture.Entity.anyProductCategory;
import static com.sysops_squad.productservice.fixture.ProductFixture.Entity.anyOtherTelevisionProduct;
import static com.sysops_squad.productservice.fixture.ProductFixture.Entity.anyTelevisionProduct;
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
        when(productService.allProductCategories()).thenReturn(List.of(anyProductCategory()));

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

    @Test
    void shouldReturnProductGivenProductId() throws Exception {
        Long productId = 1L;
        when(productService.findByProductId(productId)).thenReturn(anyTelevisionProduct());

        mockMvc.perform(get("/product/" + productId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> Assertions.assertThat(result.getResponse().getContentAsString())
                        .isEqualTo("{\"id\":" + productId + ",\"category\":\"TELEVISION\",\"name\":\"LG Television\",\"brand\":\"LG\"}"));
    }

    @Test
    void shouldReturnResponseWhenProductNotFound() throws Exception {
        Long productId = 1L;
        ProductNotFoundException exceptionThrown = new ProductNotFoundException(productId);
        when(productService.findByProductId(productId)).thenThrow(exceptionThrown);

        mockMvc.perform(get("/product/" + productId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> {
                    Exception resolvedException = result.getResolvedException();
                    Assertions.assertThat(resolvedException instanceof ResponseStatusException).isTrue();
                    Assertions.assertThat(((ResponseStatusException) resolvedException).getReason()).isEqualTo(exceptionThrown.getMessage());
                });
    }

    @Test
    void shouldReturnProductsGivenCategoryId() throws Exception {
        Long productCategoryId = 1L;
        when(productService.productsHavingCategoryId(productCategoryId)).thenReturn(List.of(anyTelevisionProduct(), anyOtherTelevisionProduct()));

        mockMvc.perform(get("/products/category/" + productCategoryId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> Assertions.assertThat(result.getResponse().getContentAsString())
                        .isEqualTo("[" +
                                "{\"id\":1,\"category\":\"" + "TELEVISION" + "\",\"name\":\"LG Television\",\"brand\":\"LG\"}," +
                                "{\"id\":2,\"category\":\"" + "TELEVISION" + "\",\"name\":\"Samsung Television\",\"brand\":\"Samsung\"}" +
                                "]"));
    }
}
