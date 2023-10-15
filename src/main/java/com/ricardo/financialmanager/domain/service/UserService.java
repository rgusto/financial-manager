package com.ricardo.financialmanager.domain.service;

import com.ricardo.financialmanager.domain.entity.UserEntity;
import com.ricardo.financialmanager.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public UserEntity findById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserEntity create(UserEntity user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    public UserEntity update(UserEntity user) {
        UserEntity userDb = this.findById(user.getId());
        userDb.setFirstName(user.getFirstName());
        userDb.setLastName(user.getLastName());
        userDb.setEmail(user.getEmail());
        userDb.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(userDb);
    }

    public void delete(UUID id) {
        UserEntity userDb = this.findById(id);
        userRepository.delete(userDb);
        userRepository.flush();
    }

}
