package com.github.shewaeger.magazstore.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "attachments")
@Data
@Accessors(chain = true)
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;

    @Column
    String mimeType;

    @Column
    String hash;

    @Column
    LocalDateTime dateCreate;

    @Column
    LocalDateTime dateUpdate;

    @JoinColumn(name = "id_owner")
    @ManyToOne(optional = false)
    User owner;

    @PrePersist
    public void onCreate() {
        dateCreate = LocalDateTime.now();
        dateUpdate = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        dateUpdate = LocalDateTime.now();
    }
}
