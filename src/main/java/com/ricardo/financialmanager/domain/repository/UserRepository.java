package com.ricardo.financialmanager.domain.repository;

import com.ricardo.financialmanager.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    public UserDetails findByLogin(String login);

}
