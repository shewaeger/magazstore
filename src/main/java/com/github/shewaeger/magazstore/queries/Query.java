package com.github.shewaeger.magazstore.queries;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Data
public abstract class Query<T> {
    private String search;

    @DecimalMin(value = "0", message = "Size cannot be null")
    private int page;

    @DecimalMin(value = "1", message = "Size cannot be less that 0")
    @NotNull(message = "Size cannot be null")
    private int size;

    public Pageable pageable() {
        return PageRequest.of(page, size);
    }

    String findAny() {
        return "%" + search + "%";
    }

    String startWith() {
        return search + "%";
    }

    String endWith() {
        return "%" + search;
    }

    public abstract Specification<T> specification();
}
