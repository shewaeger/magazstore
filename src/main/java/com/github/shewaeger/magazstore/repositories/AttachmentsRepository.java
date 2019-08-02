package com.github.shewaeger.magazstore.repositories;

import com.github.shewaeger.magazstore.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentsRepository extends JpaRepository<Attachment, Long> {
    Attachment findByHash(String hash);
}
