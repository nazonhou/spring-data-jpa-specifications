package com.nazonhou.specifications.repositories;

import com.nazonhou.specifications.dtos.GetProductsDto;
import com.nazonhou.specifications.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepository {
    Page<Product> findAll(GetProductsDto getProductsDto, Pageable pageable);
}
