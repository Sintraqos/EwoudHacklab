package com.sintraqos.portfolioproject.Messages;

import com.sintraqos.portfolioproject.Entities.AccountLibraryEntity;
import lombok.Getter;

import java.util.List;

@Getter
public class AccountLibraryEntityMessage  extends Message {
    private AccountLibraryEntity entity;
    private List<AccountLibraryEntity> entities;

    public AccountLibraryEntityMessage(AccountLibraryEntity entity, String message) {
        super(true, message);
        this.entity = entity;
    }

    public AccountLibraryEntityMessage(List<AccountLibraryEntity> entities) {
        super(true, "");
        this.entities = entities;
    }

    public AccountLibraryEntityMessage(String message) {
        super(false, message);
    }
}
