package com.github.shewaeger.magazstore.dto.category;

import com.github.shewaeger.magazstore.dto.product.ProductWrapper;
import com.github.shewaeger.magazstore.entity.Category;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(exclude = "children")
@ToString(exclude = "children")
@NoArgsConstructor
public class CategoryWrapper {
    private Long id;
    private String name;
    private List<CategoryWrapper> children = new ArrayList<>();
    private CategoryWrapper parent;
    private List<ProductWrapper> products = new ArrayList<>();

    public CategoryWrapper(Category item, boolean includeChildren) {
        toWrapper(item, includeChildren);
    }

    public void toWrapper(Category item, boolean includeChildren) {
        this.id = item.getId();
        this.name = item.getName();
        if (item.getParent() != null)
            this.parent = new CategoryWrapper(item.getParent(), false);
        if (includeChildren) {
            this.children = item.getChildren()
                    .stream()
                    .map(c -> new CategoryWrapper(c, false))
                    .collect(Collectors.toList());
        }

        this.products = item.getProducts()
                .stream()
                .map(ProductWrapper::new)
                .collect(Collectors.toList());
    }
}
