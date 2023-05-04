package com.sysops_squad.productservice.controller;

import com.sysops_squad.productservice.ProductServiceApplication;
import com.sysops_squad.productservice.fixture.ProductCategoryFixture;
import com.sysops_squad.productservice.fixture.ProductFixture;
import com.sysops_squad.productservice.model.Product;
import com.sysops_squad.productservice.model.ProductCategory;
import com.sysops_squad.productservice.repository.ProductRepository;
import com.sysops_squad.productservice.service.ProductService;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("integration-test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ProductServiceApplication.class)
public class ProductIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:11.1")
            .withDatabaseName("integration-tests-db")
            .withUsername("postgres")
            .withPassword("postgres");

//    static class Initializer
//            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
//        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
//            TestPropertyValues.of(
//                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
//                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
//                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
//            ).applyTo(configurableApplicationContext.getEnvironment());
//        }
//    }


    @Test
    void shouldReturnAllProductCategories() {
        ResponseEntity<List<ProductCategory>> productCategories = testRestTemplate.exchange(urlForEndpoint(Endpoints.productCategories)
                , HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });

        assertThat(productCategories.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(productCategories.getBody()).isEqualTo(List.of(ProductCategoryFixture.Model.anyProductCategory()));
    }

    @Test
    void shouldReturnProductsHavingAGivenProductCategoryName() {
        String productCategoryName = "TELEVISION";

        ResponseEntity<List<Product>> productsHavingCategoryName =
                testRestTemplate.exchange(urlForEndpoint(String.format(Endpoints.productsCategoryName, productCategoryName))
                        , HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                        });

        assertThat(productsHavingCategoryName.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(productsHavingCategoryName.getBody()).isEqualTo(List.of(ProductFixture.Model.anyTelevisionProductWithProductCategoryName(productCategoryName)));
    }

    @Test
    void shouldReturnProductsHavingAGivenProductCategoryId() {
        Long productCategoryId = 1L;

        ResponseEntity<List<Product>> productsHavingCategoryId =
                testRestTemplate.exchange(urlForEndpoint(String.format(Endpoints.productCategoryId, productCategoryId))
                        , HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                        });

        assertThat(productsHavingCategoryId.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(productsHavingCategoryId.getBody()).isEqualTo(List.of(ProductFixture.Model.anyTelevisionProductWithProductCategoryId(productCategoryId)));
    }

    @Test
    void shouldReturnProductsGivenProductId() {
        Long productId = 1L;

        ResponseEntity<Product> product =
                testRestTemplate.getForEntity(urlForEndpoint(String.format(Endpoints.productById, productId))
                        , Product.class);

        assertThat(product.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(product.getBody()).isEqualTo(ProductFixture.Model.anyTelevisionProduct());
    }

    @Test
    void shouldReturnProductsNotFoundGivenNonExistentProductId() {
        Long nonExistentProductId = 100L;

        ResponseEntity<Product> product =
                testRestTemplate.getForEntity(urlForEndpoint(String.format(Endpoints.productById, nonExistentProductId))
                        , Product.class);

        assertThat(product.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    private String urlForEndpoint(String endpoint) {
        return "http://localhost:" + port + endpoint;
    }

    public static class Endpoints {
        static String productCategories = "/products/categories";
        static String productsCategoryName = "/products/categoryName/%s";
        static String productCategoryId = "/products/category/%s";
        static String productById = "/product/%s";
    }
}
