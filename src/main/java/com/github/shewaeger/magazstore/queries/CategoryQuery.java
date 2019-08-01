package com.github.shewaeger.magazstore.queries;

import com.github.shewaeger.magazstore.entity.Category;
import org.springframework.data.jpa.domain.Specification;

public class CategoryQuery extends Query<Category> {
    @Override
    public Specification<Category> specification() {
        return (Specification<Category>) (root, query, cb) -> cb.like(root.get("name"), this.findAny());
    }
}
