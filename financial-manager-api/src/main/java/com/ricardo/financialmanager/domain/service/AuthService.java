package com.ricardo.financialmanager.domain.service;

import com.ricardo.financialmanager.domain.entity.UserEntity;
import com.ricardo.financialmanager.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    public void signIn(UserEntity user) {
        UserDetails userDb = loadUserByUsername(user.getLogin());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
    }

}
