package com.sintraqos.portfolioproject.Messages;

import com.sintraqos.portfolioproject.Entities.AccountLibraryEntity;
import lombok.Getter;

@Getter
public class AccountLibraryEntityMessage  extends Message {
    private AccountLibraryEntity entity;

    public AccountLibraryEntityMessage(AccountLibraryEntity entity, String message) {
        super(true, message);
        this.entity = entity;
    }
    public AccountLibraryEntityMessage(AccountLibraryEntity entity, boolean isSuccessful, String message) {
        super(isSuccessful, message);
        this.entity = entity;
    }
    public AccountLibraryEntityMessage(String message){
        super(false, message);
    }
}
