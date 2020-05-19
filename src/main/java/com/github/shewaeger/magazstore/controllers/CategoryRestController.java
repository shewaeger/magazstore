package com.github.shewaeger.magazstore.controllers;

import com.github.shewaeger.magazstore.dto.category.CategoryEditWrapper;
import com.github.shewaeger.magazstore.dto.category.CategoryWrapper;
import com.github.shewaeger.magazstore.queries.CategoryQuery;
import com.github.shewaeger.magazstore.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryRestController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("{id}")
    public CategoryWrapper get(@PathVariable Long id) {
        return categoryService.get(id);
    }

    @GetMapping("list")
    public Page<CategoryWrapper> list(CategoryQuery query) {
        return categoryService.list(query);
    }

    @PostMapping
    public CategoryWrapper add(@RequestBody CategoryEditWrapper wrapper) {
        return categoryService.add(wrapper);
    }

    @PatchMapping
    public CategoryWrapper edit(@RequestBody CategoryEditWrapper wrapper) {
        return categoryService.edit(wrapper);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
