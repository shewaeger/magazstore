package com.github.shewaeger.magazstore.services;

import com.github.shewaeger.magazstore.entity.Action;
import com.github.shewaeger.magazstore.enums.UserActions;
import com.github.shewaeger.magazstore.repositories.ActionsRepository;
import com.github.shewaeger.magazstore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private ActionsRepository actionsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @EventListener
    public void start(ContextRefreshedEvent event) {
        List<String> actionsValue = actionsRepository.findAllActionsValue();
        List<String> userActions = Arrays.stream(UserActions.values()).map(Enum::toString).collect(Collectors.toList());

        List<Long> toDeleteId = actionsValue.stream()
                .filter(
                        av -> userActions.stream().noneMatch(av::equals)
                )
                .map(av -> actionsRepository.findIdByActionValue(av))
                .collect(Collectors.toList());

        toDeleteId.forEach(id -> {
            List<Long> removedUsers = userRepository.findAllByActionId(id);
            for (Long userId : removedUsers) {
                userRepository.deleteById(userId);
            }

        });

        toDeleteId.forEach(actionsRepository::deleteId);

        List<String> toCreate = userActions.stream()
                .filter(ua -> actionsValue.stream().noneMatch(ua::equals))
                .collect(Collectors.toList());

        toCreate.forEach(val -> {
            UserActions userAction = UserActions.valueOf(val);
            Action action = new Action()
                    .setAction(userAction);
            actionsRepository.save(action);
        });

//        transactionTemplate.execute(s -> {
//                User root = userRepository.findFirstByLogin("root");
//                if (root == null) {
//                    root = new User();
//                    root.getActions().add(actionsRepository.findFirstByAction(UserActions.ROLE_ADMIN));
//                    root.setLogin("root");
//                    root.setPassword("1");
//                    userRepository.save(root);
//                }
//                return null;
//            }
//        );
    }
}
