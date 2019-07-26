package com.github.shewaeger.magazstore.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@Accessors(chain = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String login;

    @Column
    String password;

    @Column
    LocalDateTime dateCreate;

    @Column
    LocalDateTime dateUpdate;

    @PrePersist
    public void onCreate() {
        dateCreate = LocalDateTime.now();
        dateUpdate = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        dateUpdate = LocalDateTime.now();
    }

    @ManyToMany
    @JoinTable(
            name = "users_actions",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_action")
    )
    Set<Action> actions = new HashSet<>();

}
