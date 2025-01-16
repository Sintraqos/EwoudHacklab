package com.sintraqos.portfolioproject.User.UseCases;

import com.sintraqos.portfolioproject.Messages.Message;
import com.sintraqos.portfolioproject.User.UserMessage;
import com.sintraqos.portfolioproject.User.UserService;
import com.sintraqos.portfolioproject.UserLibrary.UserLibraryService;
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
    public Message banAccount(String username){
        return userService.banAccount(username);
    }

    /**
     * Unban the given account
     *
     * @param username the username of the user
     */
    public Message unbanAccount(String username) {
        return userService.unbanAccount(username);
    }

}
