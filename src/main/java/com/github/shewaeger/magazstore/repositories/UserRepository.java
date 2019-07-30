package com.github.shewaeger.magazstore.repositories;

import com.github.shewaeger.magazstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    User findFirstByLogin(String login);

    @Query(
            value = "select u.id from public.users_actions as ua inner join public.users as u on ua.id_user = u.id where ua.id_action = ?1",
            nativeQuery = true
    )
    List<Long> findAllByActionId(Long id);
}
