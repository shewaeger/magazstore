package com.github.shewaeger.magazstore.utils;

import com.github.shewaeger.magazstore.exceptions.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public class Asserts {
    public static <T> T present(JpaRepository<T, Long> rep, Long id, String message, Object... args) {
        return rep.findById(id).orElseThrow(() -> new NotFoundException(message, args));
    }

    public static <T> T notNull(T object, String message, Object... args) {
        if (object == null)
            throw new NotFoundException(message, args);
        return object;
    }
}
