package com.nazonhou.specifications.specifications;

import com.nazonhou.specifications.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class ProductSpecification {
    public static Specification<Product> nameContains(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(
                root.get("name"), name + "%");
    }

    public static Specification<Product> heightEquals(int height) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get("height"), height);
    }

    public static Specification<Product> widthEquals(int width) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get("width"), width);
    }

    public static Specification<Product> weightEquals(int weight) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get("weight"), weight);
    }

    public static Specification<Product> colorEquals(String color) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get("color"), color);
    }

    public static Specification<Product> hasCategory(UUID categoryId) {
        return (root, query, criteriaBuilder) -> {
            root.join("category");
            return criteriaBuilder.equal(
                    root.get("category").get("id"), categoryId);
        };
    }
}
