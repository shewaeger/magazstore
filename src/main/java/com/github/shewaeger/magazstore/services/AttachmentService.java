package com.github.shewaeger.magazstore.services;

import com.github.shewaeger.magazstore.dto.attachment.AttachmentWrapper;
import com.github.shewaeger.magazstore.entity.Attachment;
import com.github.shewaeger.magazstore.exceptions.NotFoundException;
import com.github.shewaeger.magazstore.exceptions.ServiceException;
import com.github.shewaeger.magazstore.repositories.AttachmentsRepository;
import com.github.shewaeger.magazstore.utils.Asserts;
import com.google.common.hash.Hashing;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class AttachmentService {

    private File storageDir;

    @Value("${storage.attachments}")
    private String storage;

    @Autowired
    private AttachmentsRepository repository;

    @Autowired
    private UserService userService;

    @EventListener
    public void contextRefreshed(ContextRefreshedEvent cre) {
        Path path = Paths.get(storage);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new ServiceException(e, "Unable to create directory");
            }
        }

        storageDir = path.toFile();
    }

    //TODO: проверка типов, ограничения на размер
    @Transactional
    public AttachmentWrapper add(MultipartFile file) {
        String hash;
        byte[] fileBytes;
        try {
            fileBytes = file.getBytes();
            //noinspection UnstableApiUsage
            hash = Hashing.sha256().hashBytes(fileBytes).toString();
        } catch (IOException e) {
            throw new ServiceException(e, "Unable to get file.");
        }
        File outFile = new File(storageDir, hash);
        if (outFile.exists()) {
            Attachment attachment = repository.findByHash(hash);
            if (attachment != null)
                return new AttachmentWrapper(attachment);
        } else {
            try {
                if (!outFile.createNewFile()) {
                    throw new ServiceException("Unable to create file");
                }
                OutputStream ostream = new FileOutputStream(outFile);
                ostream.write(fileBytes);
                ostream.close();
            } catch (IOException e) {
                throw new ServiceException(e, "Unable to create file");
            }
        }

        Attachment attachment = new Attachment();

        attachment.setHash(hash);
        attachment.setMimeType(file.getContentType());
        attachment.setName(file.getName());
        attachment.setOwner(userService.getCurrentUser());

        return new AttachmentWrapper(repository.save(attachment));
    }

    @Transactional
    public void get(String hash, HttpServletResponse response) {
        File file = new File(storageDir, hash);
        if (!file.exists()) {
            throw new NotFoundException("File %s not found", hash);
        }

        Attachment attachment = repository.findByHash(hash);
        Asserts.notNull(attachment, "File %s not found");

        FileInputStream stream;
        try {
            stream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new NotFoundException(e, "File %s not found");
        }

        response.setContentType(attachment.getMimeType());
        try {
            IOUtils.copy(stream, response.getOutputStream());
        } catch (IOException e) {
            throw new ServiceException("Unable to create response");
        }

    }
}
