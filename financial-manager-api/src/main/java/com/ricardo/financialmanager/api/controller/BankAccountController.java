package com.ricardo.financialmanager.api.controller;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import com.ricardo.financialmanager.api.contract.BankAccountAPI;
import com.ricardo.financialmanager.api.model.request.BankAccountRequest;
import com.ricardo.financialmanager.api.model.response.BankAccountResponse;
import com.ricardo.financialmanager.domain.entity.BankAccountEntity;
import com.ricardo.financialmanager.domain.service.BankAccountService;

@RestController
public class BankAccountController implements BankAccountAPI {

    private final ModelMapper modelMapper;
    private final BankAccountService bankAccountService;

    public BankAccountController(ModelMapper modelMapper, BankAccountService bankAccountService) {
        this.modelMapper = modelMapper;
        this.bankAccountService = bankAccountService;
    }

    @Override
    public List<BankAccountResponse> findAll() {
        return bankAccountService.findAll()
                .stream()
                .map(bankAccountEntity -> modelMapper.map(bankAccountEntity, BankAccountResponse.class))
                .toList();
    }

    @Override
    public ResponseEntity<BankAccountResponse> findById(UUID id) {
        return ResponseEntity.ok(modelMapper.map(bankAccountService.findById(id), BankAccountResponse.class));
    }

    @Override
    @Transactional
    public BankAccountResponse create(BankAccountRequest bankAccountRequest) {
        BankAccountEntity bankAccount = modelMapper.map(bankAccountRequest, BankAccountEntity.class);
        return modelMapper.map(bankAccountService.create(bankAccount), BankAccountResponse.class);
    }

    @Override
    @Transactional
    public ResponseEntity<BankAccountResponse> update(BankAccountRequest bankAccountRequest, UUID id) {
        BankAccountEntity bankAccount = modelMapper.map(bankAccountRequest, BankAccountEntity.class);
        bankAccount.setId(id);
        return ResponseEntity.ok(modelMapper.map(bankAccountService.update(bankAccount), BankAccountResponse.class));
    }

    @Override
    @Transactional
    public ResponseEntity<Void> delete(UUID id) {
        bankAccountService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
