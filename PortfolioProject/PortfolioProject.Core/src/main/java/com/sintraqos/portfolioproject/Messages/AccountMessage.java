package com.sintraqos.portfolioproject.Messages;

import com.sintraqos.portfolioproject.DTO.AccountDTO;
import lombok.Getter;

@Getter
public class AccountMessage extends Message {
    private AccountDTO account;

    public AccountMessage(AccountDTO account, String message) {
        super(true, message);
        this.account = account;
    }

    public AccountMessage(String message) {
        super(false, message);
    }
}
