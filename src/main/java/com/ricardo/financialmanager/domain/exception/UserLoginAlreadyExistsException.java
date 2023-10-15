package com.ricardo.financialmanager.domain.exception;

import jakarta.persistence.EntityExistsException;

import java.io.Serial;
import java.util.UUID;

public class UserLoginAlreadyExistsException extends EntityExistsException {

    @Serial
    private static final long serialVersionUID = 1L;

    public UserLoginAlreadyExistsException(String message) {
        super(message);
    }

    public UserLoginAlreadyExistsException(UUID id, String login) {
        this("Login already exists: %s".formatted(login));
    }

}
