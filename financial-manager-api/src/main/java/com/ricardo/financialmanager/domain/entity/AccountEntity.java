package com.ricardo.financialmanager.domain.entity;

import com.ricardo.financialmanager.domain.enums.AccountTypeEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity(name = "accounts")
@Table(name = "accounts")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountEntity extends AbstractEntity {

    private String name;

    private AccountTypeEnum type;

    private BigDecimal balance;

    private UserEntity user;

}
