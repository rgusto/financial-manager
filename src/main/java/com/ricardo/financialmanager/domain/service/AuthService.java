package com.ricardo.financialmanager.domain.service;

import com.ricardo.financialmanager.domain.entity.UserEntity;
import com.ricardo.financialmanager.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public UserEntity findById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    public void signIn(UserEntity user) {
        UserEntity userDb = findById(user.getId());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
    }

}
