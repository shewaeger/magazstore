package com.github.shewaeger.magazstore.dto.product;

import com.github.shewaeger.magazstore.entity.Product;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class ProductEditWrapper {
    private Long id;

    private String name;

    private String description;

    private Long category;

    private List<String> attachments = new ArrayList<>();

    public void fromWrapperSimple(Product product) {
        product.setName(name)
                .setDescription(description);
    }
}
