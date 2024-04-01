package com.ricardo.financialmanager.domain.service;

import com.ricardo.financialmanager.domain.entity.BankAccountEntity;
import com.ricardo.financialmanager.domain.exception.BankAccountNotFoundException;
import com.ricardo.financialmanager.domain.exception.EntityInUseException;
import com.ricardo.financialmanager.domain.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public List<BankAccountEntity> findAll() {
        return bankAccountRepository.findAll();
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
