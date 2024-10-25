package com.sintraqos.portfolioproject.Messages;

import com.sintraqos.portfolioproject.Entities.AccountEntity;
import lombok.Getter;

@Getter
public class AccountEntityMessage extends Message {
    private AccountEntity entity;

    public AccountEntityMessage(AccountEntity entity, String message) {
        super(true, message);
        this.entity = entity;
    }
    public AccountEntityMessage(AccountEntity entity, boolean isSuccessful, String message) {
        super(isSuccessful, message);
        this.entity = entity;
    }
    public AccountEntityMessage(String message){
        super(false, message);
    }
}
