package com.ricardo.financialmanager.api.model.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class BankAccountResponse {

    private UUID id;
    private String name;
    private String type;
    private BigDecimal balance;

}
