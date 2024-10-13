package com.ricardo.financialmanager.api.controller;

import com.ricardo.financialmanager.api.contract.UserAPI;
import com.ricardo.financialmanager.api.model.request.UserRequest;
import com.ricardo.financialmanager.api.model.request.UserUpdateRequest;
import com.ricardo.financialmanager.api.model.response.UserResponse;
import com.ricardo.financialmanager.domain.entity.UserEntity;
import com.ricardo.financialmanager.domain.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController implements UserAPI {

    private final ModelMapper modelMapper;

    private final UserService userService;

    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public List<UserResponse> findAll() {
        return userService.findAll()
                .stream()
                .map(userEntity -> modelMapper.map(userEntity, UserResponse.class))
                .toList();
    }

    @Override
    public ResponseEntity<UserResponse> findById(UUID id) {
        return ResponseEntity.ok(modelMapper.map(userService.findById(id), UserResponse.class));
    }

    @Override
    @Transactional
    public UserResponse create(UserRequest userRequest) {
        UserEntity user = modelMapper.map(userRequest, UserEntity.class);
        return modelMapper.map(userService.create(user), UserResponse.class);
    }

    @Override
    @Transactional
    public ResponseEntity<UserResponse> update(UserUpdateRequest userRequest, UUID id) {
        UserEntity user = modelMapper.map(userRequest, UserEntity.class);
        user.setId(id);
        return ResponseEntity.ok(modelMapper.map(userService.update(user), UserResponse.class));
    }

    @Override
    @Transactional
    public ResponseEntity<?> delete(UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
