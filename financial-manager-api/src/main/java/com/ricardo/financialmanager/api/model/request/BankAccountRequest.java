package com.ricardo.financialmanager.api.model.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class BankAccountRequest {

    private String name;
    private String type;
    private BigDecimal balance;
    private UUID userId;

}
