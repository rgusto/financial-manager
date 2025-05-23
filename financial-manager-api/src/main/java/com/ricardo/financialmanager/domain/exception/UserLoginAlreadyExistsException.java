package com.ricardo.financialmanager.domain.exception;

import java.io.Serial;

public class UserLoginAlreadyExistsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public UserLoginAlreadyExistsException(String login) {
        super("Login already exists: %s".formatted(login));
    }
}
