package com.github.shewaeger.magazstore.services;

import com.github.shewaeger.magazstore.dto.category.CategoryEditWrapper;
import com.github.shewaeger.magazstore.dto.category.CategoryWrapper;
import com.github.shewaeger.magazstore.entity.Category;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

    @Transactional
    public CategoryWrapper add(CategoryEditWrapper wrapper) {
        Category category = new Category();
        wrapper.fromWrapperSimple(category);

        return new CategoryWrapper();

    }

}
