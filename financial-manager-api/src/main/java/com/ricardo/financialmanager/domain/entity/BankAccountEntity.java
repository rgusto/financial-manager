package com.ricardo.financialmanager.domain.entity;

import java.math.BigDecimal;
import java.util.Objects;

import com.ricardo.financialmanager.domain.enums.BankAccountTypeEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "bank_account")
@Table(name = "bank_account")
public class BankAccountEntity extends AbstractEntity {

    private String name;

    @Enumerated(EnumType.STRING)
    private BankAccountTypeEnum type;

    private BigDecimal balance;

    @ManyToOne
    private UserEntity user;

    public BankAccountEntity() {
    }

    public BankAccountEntity(String name, BankAccountTypeEnum type, BigDecimal balance, UserEntity user) {
        this.name = name;
        this.type = type;
        this.balance = balance;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BankAccountTypeEnum getType() {
        return type;
    }

    public void setType(BankAccountTypeEnum type) {
        this.type = type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BankAccountEntity)) return false;
        if (!super.equals(o)) return false;
        BankAccountEntity that = (BankAccountEntity) o;
        return Objects.equals(name, that.name) &&
               type == that.type &&
               Objects.equals(balance, that.balance) &&
               Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, type, balance, user);
    }

    @Override
    public String toString() {
        return "BankAccountEntity{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", balance=" + balance +
                ", user=" + user +
                '}';
    }
}