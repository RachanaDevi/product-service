package com.sysops_squad.productservice.repository;

import com.sysops_squad.productservice.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.sysops_squad.productservice.fixture.ProductCategoryFixture.anyTelevisionCategory;
import static com.sysops_squad.productservice.fixture.ProductFixture.anyTelevisionProductWithProductCategoryId;
import static com.sysops_squad.productservice.fixture.ProductFixture.anyTelevisionProductWithProductCategoryName;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryIntegrationTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

//    @BeforeEach
//    void setUp() {
//        this.productCategoryRepository.save(anyProductCategoryWith(anyTelevisionCategory()));
//        this.productRepository.save(anyTelevisionProductWithProductCategoryName(anyTelevisionCategory()));
//    }
//
//    @AfterEach
//    void tearDown() {
//        this.productRepository.deleteAll();
//        this.productCategoryRepository.deleteAll();
//    }

    @Test
    @Transactional
    void shouldReturnProductsByCategoryName() {
        String categoryName = anyTelevisionCategory();

        List<Product> products = productRepository.findByCategoryName(categoryName);

        assertThat(products).containsExactly(anyTelevisionProductWithProductCategoryName(categoryName));
    }

    @Test
    @Transactional
    void shouldReturnProductsByCategoryId() {
        Long categoryId = 1L;

        List<Product> products = productRepository.findByCategoryId(categoryId);

        assertThat(products).containsExactly(anyTelevisionProductWithProductCategoryId(categoryId));
    }

    @Test
    @Transactional
    void shouldReturnNoProductsGivenCategoryDoesNotExist() {
        String nonExistentProductCategoryName = "GAMING";

        List<Product> products = productRepository.findByCategoryName(nonExistentProductCategoryName);

        assertThat(products).isEmpty();
    }
}