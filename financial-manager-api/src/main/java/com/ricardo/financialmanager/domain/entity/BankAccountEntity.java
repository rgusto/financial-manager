package com.ricardo.financialmanager.domain.entity;

import com.ricardo.financialmanager.domain.enums.BankAccountTypeEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity(name = "bank_account")
@Table(name = "bank_account")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankAccountEntity extends AbstractEntity {

    private String name;

    @Enumerated(EnumType.STRING)
    private BankAccountTypeEnum type;

    private BigDecimal balance;

    @ManyToOne
    private UserEntity user;

}
