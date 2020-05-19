package com.github.shewaeger.magazstore.configurations.properties;

import com.github.shewaeger.magazstore.utils.StringUtils;

import javax.validation.constraints.Pattern;

public class AttachmentsBans {

    @Pattern(regexp = "^\\s*(?<size>\\d+)\\s*(?<factor>(GB|MB|KB)?)?$", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String size;

    private Long length = null;

    public AttachmentsBans setSize(String size) {
        this.size = size;
        this.length = StringUtils.parseSize(size);
        return this;
    }

    public Long getUploadLimit() {
        return length;
    }
}
