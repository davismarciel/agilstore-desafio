package com.davi.agileStore.util;

import com.davi.agileStore.entities.Product;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationUtil {
    public static Specification<Product> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                name == null ? null : criteriaBuilder.equal(criteriaBuilder.lower(root.get("name")), name.toLowerCase());
    }

    public static Specification<Product> hasPrice(Double price) {
        return (root, query, criteriaBuilder) ->
                price == null ? null : criteriaBuilder.equal(root.get("price"), price);
    }

    public static Specification<Product> hasCategory(String category) {
        return (root, query, criteriaBuilder) -> {
            if (category == null) {
                return null;
            }

            // Join with categories
            Join<Object, Object> categories = root.join("categories");

            // Filter by category name (ignoring case)
            return criteriaBuilder.equal(criteriaBuilder.lower(categories.get("name")), category.toLowerCase());
        };
    }

}
