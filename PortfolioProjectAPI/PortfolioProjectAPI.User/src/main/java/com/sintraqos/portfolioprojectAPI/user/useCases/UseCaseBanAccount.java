package com.sintraqos.portfolioprojectAPI.user.useCases;

import com.sintraqos.portfolioprojectAPI.user.entities.UserMessage;
import com.sintraqos.portfolioprojectAPI.user.service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Usecase for handling the (un)banning of accounts
 */
@Getter
@Component
public class UseCaseBanAccount {
    private final UserService userService;

    @Autowired
    public UseCaseBanAccount(UserService userService ) {
        this.userService = userService;
    }

    /**
     * Ban the given account
     *
     * @param username the username of the user
     */
    public UserMessage banAccount(String username){
        return userService.banAccount(username);
    }

    /**
     * Unban the given account
     *
     * @param username the username of the user
     */
    public UserMessage unbanAccount(String username) {
        return userService.unbanAccount(username);
    }
}
