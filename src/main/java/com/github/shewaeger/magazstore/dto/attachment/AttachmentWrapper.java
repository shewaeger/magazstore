package com.github.shewaeger.magazstore.dto.attachment;

import com.github.shewaeger.magazstore.entity.Attachment;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AttachmentWrapper {
    private Long id;
    private String hash;

    public AttachmentWrapper(Attachment item) {
        this.id = item.getId();
        this.hash = item.getHash();
    }
}
