package com.ricardo.financialmanager.domain.exception;

import java.io.Serial;

public class UserEmailAlreadyExistsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public UserEmailAlreadyExistsException(String email) {
        super("E-mail already exists: %s".formatted(email));
    }
}
