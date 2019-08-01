package com.github.shewaeger.magazstore.dto.product;

import com.github.shewaeger.magazstore.dto.attachment.AttachmentWrapper;
import com.github.shewaeger.magazstore.dto.category.CategoryWrapper;
import com.github.shewaeger.magazstore.entity.Product;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Accessors(chain = true)
public class ProductWrapper {
    private Long id;

    private String name;

    private String description;

    private CategoryWrapper category;

    private List<AttachmentWrapper> attachments = new ArrayList<>();

    public ProductWrapper(Product item) {
        this.id = item.getId();
        this.name = item.getName();
        this.description = item.getDescription();

        if (item.getCategory() != null)
            category = new CategoryWrapper(item.getCategory(), false);

        attachments = item.getAttachments().stream().map(AttachmentWrapper::new).collect(Collectors.toList());
    }
}
