package com.ricardo.financialmanager.domain.repository;

import com.ricardo.financialmanager.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {


}
