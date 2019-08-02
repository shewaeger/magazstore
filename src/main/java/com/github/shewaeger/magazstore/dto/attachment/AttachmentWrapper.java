package com.github.shewaeger.magazstore.dto.attachment;

import com.github.shewaeger.magazstore.entity.Attachment;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AttachmentWrapper {
    private String hash;

    private String mimeType;

    public AttachmentWrapper(Attachment item) {
        this.hash = item.getHash();
        this.mimeType = item.getMimeType();
    }
}
