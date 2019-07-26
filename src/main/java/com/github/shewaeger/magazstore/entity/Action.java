package com.github.shewaeger.magazstore.entity;

import com.github.shewaeger.magazstore.enums.UserActions;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "actions")
@Data
@Accessors(chain = true)
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    @Enumerated(EnumType.STRING)
    UserActions action;

    @ManyToMany(mappedBy = "actions")
    Set<User> users = new HashSet<>();
}
