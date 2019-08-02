package com.github.shewaeger.magazstore.services;

import com.github.shewaeger.magazstore.dto.product.ProductEditWrapper;
import com.github.shewaeger.magazstore.dto.product.ProductWrapper;
import com.github.shewaeger.magazstore.entity.Attachment;
import com.github.shewaeger.magazstore.entity.Category;
import com.github.shewaeger.magazstore.entity.Product;
import com.github.shewaeger.magazstore.exceptions.ServiceException;
import com.github.shewaeger.magazstore.queries.Query;
import com.github.shewaeger.magazstore.repositories.AttachmentsRepository;
import com.github.shewaeger.magazstore.repositories.CategoryRepository;
import com.github.shewaeger.magazstore.repositories.ProductRepository;
import com.github.shewaeger.magazstore.utils.Asserts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AttachmentsRepository attachmentsRepository;

    public ProductWrapper get(Long id) {
        Product product = Asserts.present(repository, id, "Product not found", id);
        return new ProductWrapper(product);
    }

    public ProductWrapper add(ProductEditWrapper wrapper) {
        Product product = new Product();
        wrapper.fromWrapperSimple(product);

        if (wrapper.getCategory() != null) {
            Category category = Asserts.present(
                    categoryRepository,
                    wrapper.getCategory(),
                    "Category not found",
                    wrapper.getCategory()
            );
            product.setCategory(category);
        }

        List<Attachment> attachments = attachmentsRepository.findByHashIn(wrapper.getAttachments());
        product.getAttachments().addAll(attachments);

        return new ProductWrapper(repository.save(product));
    }

    public ProductWrapper edit(ProductEditWrapper wrapper) {
        if (wrapper.getId() == null)
            throw new ServiceException("Product with id that equal null");

        Product product = Asserts.present(repository, wrapper.getId(), "Product not found");
        wrapper.fromWrapperSimple(product);

        if (wrapper.getCategory() != null) {
            Category category = Asserts.present(categoryRepository, wrapper.getCategory(), "Category not found");
            product.setCategory(category);
        } else {
            product.setCategory(null);
        }

        List<Attachment> attachments = attachmentsRepository.findByHashIn(wrapper.getAttachments());

        product.setAttachments(new HashSet<>(attachments));

        return new ProductWrapper(repository.save(product));
    }

    public void delete(Long id) {
        Product product = Asserts.present(repository, id, "Product not found");
        repository.delete(product);
    }

    public Page<ProductWrapper> list(Query<Product> query) {
        return repository.findAll(query.specification(), query.pageable())
                .map(ProductWrapper::new);
    }
}
