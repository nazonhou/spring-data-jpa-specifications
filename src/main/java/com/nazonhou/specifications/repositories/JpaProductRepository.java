package com.nazonhou.specifications.repositories;

import com.nazonhou.specifications.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface JpaProductRepository extends JpaRepository<Product, UUID>
        , JpaSpecificationExecutor<Product> {
}
