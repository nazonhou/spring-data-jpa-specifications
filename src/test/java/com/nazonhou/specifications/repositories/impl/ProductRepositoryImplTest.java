package com.nazonhou.specifications.repositories.impl;

import com.github.javafaker.Faker;
import com.nazonhou.specifications.dtos.GetProductsDto;
import com.nazonhou.specifications.entities.Category;
import com.nazonhou.specifications.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = {"spring.datasource.url=jdbc:tc:postgresql:16.2-alpine:///db"})
@Import({ProductRepositoryImpl.class})
class ProductRepositoryImplTest {
    private final Faker faker = new Faker();
    @Autowired
    ProductRepositoryImpl productRepository;
    @Autowired
    TestEntityManager testEntityManager;
    private Category category;

    @BeforeEach
    void setUp() {
        category = testEntityManager.persistAndFlush(
                Category.builder().name(faker.lorem().word()).build()
        );
    }

    @Test
    void givenProductExists_whenFindAll_thenGetProduct() {
        Product product = testEntityManager.persistAndFlush(getProduct());
        testEntityManager.persistAndFlush(getProduct());

        GetProductsDto getProductsDto = GetProductsDto.builder()
                .name(product.getName())
                .width(product.getWidth())
                .weight(product.getWeight())
                .color(product.getColor())
                .height(product.getHeight())
                .categoryId(category.getId())
                .build();
        Pageable pageable = getPageable();

        Page<Product> result = productRepository.findAll(getProductsDto, pageable);

        assertThat(result.getTotalElements()).isEqualTo(1L);
        assertThat(result.getContent().size()).isEqualTo(1);
        assertThat(result.getContent().getFirst().getId()).isEqualTo(product.getId());
    }

    @Test
    void givenProductDoesNotExists_whenFindAll_GetEmptyPage(){
        testEntityManager.persistAndFlush(getProduct());

        GetProductsDto getProductsDto = GetProductsDto.builder()
                .name(faker.commerce().productName())
                .color(faker.color().name())
                .width(faker.number().numberBetween(1, 3))
                .height(faker.number().numberBetween(1, 3))
                .weight(faker.number().numberBetween(1, 10))
                .categoryId(UUID.randomUUID())
                .build();
        Pageable pageable = getPageable();

        Page<Product> result = productRepository.findAll(getProductsDto, pageable);

        assertThat(result.getTotalElements()).isEqualTo(0L);
        assertThat(result.getContent().size()).isEqualTo(0);
    }

    private Product getProduct() {
        return Product.builder()
                .name(faker.commerce().productName())
                .category(category)
                .color(faker.color().name())
                .width(faker.number().numberBetween(1, 3))
                .height(faker.number().numberBetween(1, 3))
                .weight(faker.number().numberBetween(1, 10))
                .build();
    }

    private Pageable getPageable(){
        return PageRequest.of(0, 10, Sort.by("createdAt").descending());
    }

}