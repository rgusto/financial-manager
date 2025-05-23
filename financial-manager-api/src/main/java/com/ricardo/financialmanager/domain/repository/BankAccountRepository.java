package com.ricardo.financialmanager.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ricardo.financialmanager.domain.entity.BankAccountEntity;

public interface BankAccountRepository extends JpaRepository<BankAccountEntity, UUID> {
}
