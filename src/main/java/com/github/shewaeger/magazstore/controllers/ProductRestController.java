package com.github.shewaeger.magazstore.controllers;

import com.github.shewaeger.magazstore.dto.product.ProductEditWrapper;
import com.github.shewaeger.magazstore.dto.product.ProductWrapper;
import com.github.shewaeger.magazstore.queries.ProductQuery;
import com.github.shewaeger.magazstore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    @Autowired
    private ProductService service;

    @GetMapping("{id}")
    public ProductWrapper get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    public ProductWrapper add(@RequestBody ProductEditWrapper wrapper) {
        return service.add(wrapper);
    }

    @PatchMapping
    public ProductWrapper edit(@RequestBody ProductEditWrapper wrapper) {
        return service.edit(wrapper);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("list")
    public Page<ProductWrapper> list(ProductQuery query) {
        return service.list(query);
    }
}
