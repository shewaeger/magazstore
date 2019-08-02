package com.github.shewaeger.magazstore.services;

import com.github.shewaeger.magazstore.entity.User;
import com.github.shewaeger.magazstore.repositories.ActionsRepository;
import com.github.shewaeger.magazstore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private ActionsRepository actionsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DatabaseInitializer databaseInitializer;

    @EventListener
    public void start(ContextRefreshedEvent event) {
        databaseInitializer.initUser();
    }

    public void add() {

    }

    public User getCurrentUser() {
        return userRepository.findFirstByLogin("root");
    }
}
