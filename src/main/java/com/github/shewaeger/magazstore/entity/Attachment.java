package com.github.shewaeger.magazstore.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "attachments")
@Data
@Accessors(chain = true)
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String mimeType;

    @Column
    String hash;

    @JoinColumn(name = "id_owner")
    @ManyToOne(optional = false)
    User owner;
}
