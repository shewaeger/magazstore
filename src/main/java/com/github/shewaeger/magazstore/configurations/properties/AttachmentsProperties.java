package com.github.shewaeger.magazstore.configurations.properties;

import com.github.shewaeger.magazstore.exceptions.ServiceException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@ConfigurationProperties("attachments")
public class AttachmentsProperties {

    private String storage;

    @Getter
    @Setter
    private AttachmentsBans bans;

    @Getter
    private File storageDir;

    public void setStorage(String storage) {
        Path path = Paths.get(storage);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new ServiceException(e, "Unable to create directory %s", storage);
            }
        }
        storageDir = path.toFile();
    }
}
