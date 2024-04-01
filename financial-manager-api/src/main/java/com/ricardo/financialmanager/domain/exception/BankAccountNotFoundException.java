package com.ricardo.financialmanager.domain.exception;

import jakarta.persistence.EntityNotFoundException;

import java.io.Serial;
import java.util.UUID;

public class BankAccountNotFoundException extends EntityNotFoundException {

    @Serial
    private static final long serialVersionUID = 1L;

    public BankAccountNotFoundException(String message) {
        super(message);
    }

    public BankAccountNotFoundException(UUID id) {
        this("Bank account with id %s not found".formatted(id));
    }

}
