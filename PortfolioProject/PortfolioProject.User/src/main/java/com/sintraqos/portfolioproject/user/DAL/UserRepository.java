package com.sintraqos.portfolioproject.user.DAL;

import com.sintraqos.portfolioproject.caching.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Caching(evict = {@CacheEvict(value = CacheConfig.USER_CACHE, key = "#username")})
    UserEntity findByUsername(String username);

    @Caching(evict = {@CacheEvict(value = CacheConfig.USER_CACHE, key = "#eMail")})
    UserEntity findByEmail(String eMail);

    @Caching(evict = {@CacheEvict(value = CacheConfig.USER_CACHE, key = "#accountID")})
    UserEntity findByAccountID(int accountID);

    @Caching(evict = {@CacheEvict(value = CacheConfig.USER_CACHE, key = "#username")})
    List<UserEntity> findByUsernameContaining(String username);
}