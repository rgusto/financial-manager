package com.ricardo.financialmanager.domain.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ricardo.financialmanager.domain.entity.BankAccountEntity;
import com.ricardo.financialmanager.domain.exception.BankAccountNotFoundException;
import com.ricardo.financialmanager.domain.exception.EntityInUseException;
import com.ricardo.financialmanager.domain.repository.BankAccountRepository;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public Page<BankAccountEntity> findAll(Pageable pageable) {
        return bankAccountRepository.findAll(pageable);
    }

    public BankAccountEntity findById(UUID id) {
        return bankAccountRepository.findById(id).orElseThrow(() -> new BankAccountNotFoundException(id));
    }

    @Transactional
    public BankAccountEntity create(BankAccountEntity bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    @Transactional
    public BankAccountEntity update(BankAccountEntity bankAccount) {
        BankAccountEntity bankAccountDb = this.findById(bankAccount.getId());
        bankAccountDb.setName(bankAccount.getName());
        bankAccountDb.setType(bankAccount.getType());
        bankAccountDb.setBalance(bankAccount.getBalance());
        bankAccountDb.setUpdatedAt(LocalDateTime.now());
        return bankAccountRepository.save(bankAccountDb);
    }

    @Transactional
    public void delete(UUID id) {
        try {
            BankAccountEntity bankAccountDb = this.findById(id);
            bankAccountRepository.delete(bankAccountDb);
            bankAccountRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new BankAccountNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException("BankAccount with id %s is in use and cannot be removed".formatted(id));
        }
    }

}
