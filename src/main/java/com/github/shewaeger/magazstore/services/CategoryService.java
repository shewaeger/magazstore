package com.github.shewaeger.magazstore.services;

import com.github.shewaeger.magazstore.dto.category.CategoryEditWrapper;
import com.github.shewaeger.magazstore.dto.category.CategoryWrapper;
import com.github.shewaeger.magazstore.entity.Category;
import com.github.shewaeger.magazstore.entity.Product;
import com.github.shewaeger.magazstore.exceptions.ServiceException;
import com.github.shewaeger.magazstore.queries.Query;
import com.github.shewaeger.magazstore.repositories.CategoryRepository;
import com.github.shewaeger.magazstore.repositories.ProductRepository;
import com.github.shewaeger.magazstore.utils.Asserts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public CategoryWrapper get(Long id) {
        Category category = Asserts.present(categoryRepository, id, "Product with id #%d not found", id);
        return new CategoryWrapper(category, false);
    }

    @Transactional
    public CategoryWrapper add(CategoryEditWrapper wrapper) {
        Category category = new Category();
        wrapper.fromWrapperSimple(category);
        if (wrapper.getIdOwner() != null) {
            Category parent = Asserts.present(categoryRepository, wrapper.getIdOwner(), "Owner not found");
            category.setParent(parent);
        }
        return new CategoryWrapper(categoryRepository.save(category), true);
    }

    @Transactional
    public CategoryWrapper edit(CategoryEditWrapper wrapper) {
        if (wrapper.getId() == null)
            throw new ServiceException("Category with id that equals null");
        Category category = Asserts.present(categoryRepository, wrapper.getId(), "Category not found");

        wrapper.fromWrapperSimple(category);

        if (wrapper.getIdOwner() != null) {
            Category parent = Asserts.present(categoryRepository, wrapper.getIdOwner(), "Owner not found");
            if (isLoopDep(category, parent))
                throw new ServiceException("Loop dependency.");
            category.setParent(parent);
        } else {
            category.setParent(null);
        }

        category.getProducts().clear();

        for (Long id : wrapper.getProducts()) {
            try {
                Product product = Asserts.present(productRepository, id, "Product with id #%d not found", id);
                if (product.getCategory() != null)
                    continue;
                category.getProducts().add(product);
            } catch (ServiceException ignored) {
            }
        }

        return new CategoryWrapper(categoryRepository.save(category), true);
    }

    public void delete(Long id) {
        Product product = Asserts.present(productRepository, id, "Product with id #%d not found", id);
        productRepository.delete(product);
    }

    private boolean isLoopDep(Category category, Category parent) {
        parent = parent.getParent();
        while (parent != null) {
            if (parent.getId().equals(category.getId()))
                return true;
            parent = parent.getParent();
        }
        return false;
    }

    public Page<CategoryWrapper> list(Query<Category> query) {
        return categoryRepository.findAll(query.specification(), query.pageable())
                .map(c -> new CategoryWrapper(c, false));
    }
}
