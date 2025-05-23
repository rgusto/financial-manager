package com.ricardo.financialmanager.domain.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ricardo.financialmanager.domain.entity.UserEntity;
import com.ricardo.financialmanager.domain.exception.EntityInUseException;
import com.ricardo.financialmanager.domain.exception.UserEmailAlreadyExistsException;
import com.ricardo.financialmanager.domain.exception.UserLoginAlreadyExistsException;
import com.ricardo.financialmanager.domain.exception.UserNotFoundException;
import com.ricardo.financialmanager.domain.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public UserEntity findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public UserEntity create(UserEntity user) {
        Optional<UserEntity> userDb = userRepository.findByEmail(user.getEmail());
        if (userDb.isPresent()) {
            throw new UserEmailAlreadyExistsException(user.getEmail());
        }
        UserDetails userDetails = userRepository.findByLogin(user.getLogin());
        if (Objects.nonNull(userDetails)) {
            throw new UserLoginAlreadyExistsException(user.getLogin());
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    public UserEntity update(UserEntity user) {
        UserEntity userDb = this.findById(user.getId());
        userDb.setFirstName(user.getFirstName());
        userDb.setLastName(user.getLastName());
        userDb.setEmail(user.getEmail());
        userDb.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(userDb);
    }

    @Transactional
    public void delete(UUID id) {
        try {
            UserEntity userDb = this.findById(id);
            userRepository.delete(userDb);
            userRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException("User with id %s is in use and cannot be removed".formatted(id));
        }
    }

}
