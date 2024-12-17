package com.sintraqos.portfolioproject.Repositories;

import com.sintraqos.portfolioproject.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUsername(String username);

    UserEntity findByAccountID(int accountID);

    List<UserEntity> findByUsernameContaining(String username);
}