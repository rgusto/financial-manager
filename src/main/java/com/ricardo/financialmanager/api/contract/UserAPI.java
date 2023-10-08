package com.ricardo.financialmanager.api.contract;

import com.ricardo.financialmanager.api.model.response.UserResponse;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/users")
public interface UserAPI {

    @GetMapping
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserResponse> findAll();

    @GetMapping("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<UserResponse> findById(@PathVariable UUID id);


}
