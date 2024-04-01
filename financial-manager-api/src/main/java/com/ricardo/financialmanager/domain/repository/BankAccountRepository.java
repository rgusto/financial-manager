package com.ricardo.financialmanager.domain.repository;

import com.ricardo.financialmanager.domain.entity.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BankAccountRepository extends JpaRepository<BankAccountEntity, UUID> {


}
