package com.ricardo.financialmanager.domain.exception;

import jakarta.persistence.EntityExistsException;

import java.io.Serial;
import java.util.UUID;

public class UserEmailAlreadyExistsException extends EntityExistsException {

    @Serial
    private static final long serialVersionUID = 1L;

    public UserEmailAlreadyExistsException(String message) {
        super(message);
    }

    public UserEmailAlreadyExistsException(UUID id, String email) {
        this("E-mail already exists: %s".formatted(email));
    }

}
