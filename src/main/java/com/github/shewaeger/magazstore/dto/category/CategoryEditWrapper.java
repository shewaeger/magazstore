package com.github.shewaeger.magazstore.dto.category;

import com.github.shewaeger.magazstore.entity.Category;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class CategoryEditWrapper {
    private Long id;

    @NotEmpty(message = "The name cannot be empty.")
    private String name;

    private Long idOwner;

    private List<Long> products = new ArrayList<>();

    public CategoryEditWrapper() {
    }

    public void fromWrapperSimple(Category item) {
        item.setName(this.name);
    }
}
