package com.ricardo.financialmanager.api.controller;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ricardo.financialmanager.api.contract.BankAccountAPI;
import com.ricardo.financialmanager.api.model.request.BankAccountRequest;
import com.ricardo.financialmanager.api.model.response.BankAccountResponse;
import com.ricardo.financialmanager.domain.entity.BankAccountEntity;
import com.ricardo.financialmanager.domain.service.BankAccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/bank-accounts")
public class BankAccountController implements BankAccountAPI {

    private final ModelMapper modelMapper;
    private final BankAccountService bankAccountService;

    @Autowired
    public BankAccountController(ModelMapper modelMapper, BankAccountService bankAccountService) {
        this.modelMapper = modelMapper;
        this.bankAccountService = bankAccountService;
    }

    @Override
    public Page<BankAccountResponse> findAll(Pageable pageable) {
        return bankAccountService.findAll(pageable)
                .map(bankAccountEntity -> modelMapper.map(bankAccountEntity, BankAccountResponse.class));
    }

    @Override
    public ResponseEntity<BankAccountResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(modelMapper.map(bankAccountService.findById(id), BankAccountResponse.class));
    }

    @Override
    public BankAccountResponse create(@RequestBody @Valid BankAccountRequest bankAccountRequest) {
        BankAccountEntity bankAccount = modelMapper.map(bankAccountRequest, BankAccountEntity.class);
        return modelMapper.map(bankAccountService.create(bankAccount), BankAccountResponse.class);
    }

    @Override
    public ResponseEntity<BankAccountResponse> update(@RequestBody @Valid BankAccountRequest bankAccountRequest, @PathVariable UUID id) {
        BankAccountEntity bankAccount = modelMapper.map(bankAccountRequest, BankAccountEntity.class);
        bankAccount.setId(id);
        return ResponseEntity.ok(modelMapper.map(bankAccountService.update(bankAccount), BankAccountResponse.class));
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        bankAccountService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
