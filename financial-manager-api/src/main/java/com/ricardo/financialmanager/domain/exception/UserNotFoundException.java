package com.ricardo.financialmanager.domain.exception;

import jakarta.persistence.EntityNotFoundException;

import java.io.Serial;
import java.util.UUID;

public class UserNotFoundException extends EntityNotFoundException {

    @Serial
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(UUID id) {
        this("User with id %s not found".formatted(id));
    }

}
