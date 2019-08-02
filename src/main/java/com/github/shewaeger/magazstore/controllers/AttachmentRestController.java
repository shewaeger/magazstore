package com.github.shewaeger.magazstore.controllers;

import com.github.shewaeger.magazstore.dto.attachment.AttachmentWrapper;
import com.github.shewaeger.magazstore.services.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/attachment")
public class AttachmentRestController {

    @Autowired
    private AttachmentService attachmentService;

    @PostMapping
    public AttachmentWrapper add(@RequestBody MultipartFile file) {
        return attachmentService.add(file);
    }

    @GetMapping("{hash}")
    public void get(@PathVariable String hash, HttpServletResponse response) {
        attachmentService.get(hash, response);
    }


}
