package com.github.shewaeger.magazstore.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;

    @Column
    String description;

    @JoinColumn(name = "id_parent")
    @ManyToOne
    Category category;

    @ManyToMany
    @JoinTable(
            name = "products_attachments",
            joinColumns = @JoinColumn(name = "id_product"),
            inverseJoinColumns = @JoinColumn(name = "id_attachment")
    )
    Set<Attachment> attachments = new HashSet<>();
}
