package com.github.shewaeger.magazstore.queries;

import com.github.shewaeger.magazstore.entity.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductQuery extends Query<Product> {
    @Override
    public Specification<Product> specification() {
        return (Specification<Product>) (root, query, cb) -> cb.like(root.get("name"), findAny());
    }
}
