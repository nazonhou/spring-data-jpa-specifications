package com.nazonhou.specifications.repositories.impl;

import com.nazonhou.specifications.dtos.GetProductsDto;
import com.nazonhou.specifications.entities.Product;
import com.nazonhou.specifications.repositories.JpaProductRepository;
import com.nazonhou.specifications.repositories.ProductRepository;
import com.nazonhou.specifications.specifications.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final JpaProductRepository jpaProductRepository;

    @Override
    public Page<Product> findAll(GetProductsDto getProductsDto, Pageable pageable) {
        List<Specification<Product>> specifications = new ArrayList<>();

        if (getProductsDto.getName() != null) {
            specifications.add(ProductSpecification.nameContains(getProductsDto.getName()));
        }

        if (getProductsDto.getColor() != null) {
            specifications.add(ProductSpecification.colorEquals(getProductsDto.getColor()));
        }

        if (getProductsDto.getWidth() != null) {
            specifications.add(ProductSpecification.widthEquals(getProductsDto.getWidth()));
        }

        if (getProductsDto.getWeight() != null) {
            specifications.add(ProductSpecification.weightEquals(getProductsDto.getWeight()));
        }

        if (getProductsDto.getHeight() != null) {
            specifications.add(ProductSpecification.heightEquals(getProductsDto.getHeight()));
        }

        if (getProductsDto.getCategoryId() != null) {
            specifications.add(ProductSpecification.hasCategory(getProductsDto.getCategoryId()));
        }

        return jpaProductRepository.findAll(Specification.allOf(specifications), pageable);
    }
}
