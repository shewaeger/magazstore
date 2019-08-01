package com.github.shewaeger.magazstore.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;

    @JoinColumn(name = "id_parent")
    @ManyToOne
    Category parent;

    @Column
    LocalDateTime dateCreate;

    @Column
    LocalDateTime dateUpdate;

    @OneToMany(mappedBy = "parent")
    Set<Category> children = new HashSet<>();

    @OneToMany(mappedBy = "category")
    Set<Product> products = new HashSet<>();

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
