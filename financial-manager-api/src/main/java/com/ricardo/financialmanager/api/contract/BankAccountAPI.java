package com.ricardo.financialmanager.api.contract;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ricardo.financialmanager.api.model.request.BankAccountRequest;
import com.ricardo.financialmanager.api.model.response.BankAccountResponse;

import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@RequestMapping("/api/v1/bank-accounts")
public interface BankAccountAPI {

    @GetMapping
    @Produces(MediaType.APPLICATION_JSON)
    public Page<BankAccountResponse> findAll(Pageable pageable);

    @GetMapping("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<BankAccountResponse> findById(@PathVariable UUID id);

    @PostMapping
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public BankAccountResponse create(@RequestBody @Valid BankAccountRequest bankAccountRequest);

    @PutMapping("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<BankAccountResponse> update(@RequestBody @Valid BankAccountRequest bankAccountRequest, @PathVariable UUID id);

    @DeleteMapping("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable UUID id);

}
