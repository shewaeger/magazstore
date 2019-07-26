package com.github.shewaeger.magazstore.repositories;

import com.github.shewaeger.magazstore.entity.Action;
import com.github.shewaeger.magazstore.enums.UserActions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActionsRepository extends JpaRepository<Action, Long> {
    Action findFirstByAction(UserActions action);

    @Query(value = "Select action FROM actions", nativeQuery = true)
    List<String> findAllActionsValue();

    @Query(value = "select id from actions where action = :action", nativeQuery = true)
    Long findIdByActionValue(@Param("action") String action);

    @Modifying
    @Query(value = "delete from actions where id = ?1", nativeQuery = true)
    void deleteId(Long id);
}
