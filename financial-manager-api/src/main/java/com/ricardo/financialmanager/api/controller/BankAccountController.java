package com.ricardo.financialmanager.api.controller;

import com.ricardo.financialmanager.api.contract.BankAccountAPI;
import com.ricardo.financialmanager.api.model.request.BankAccountRequest;
import com.ricardo.financialmanager.api.model.response.BankAccountResponse;
import com.ricardo.financialmanager.domain.entity.BankAccountEntity;
import com.ricardo.financialmanager.domain.service.BankAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class BankAccountController implements BankAccountAPI {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BankAccountService bankAccountService;

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
    public ResponseEntity<?> delete(UUID id) {
        bankAccountService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
