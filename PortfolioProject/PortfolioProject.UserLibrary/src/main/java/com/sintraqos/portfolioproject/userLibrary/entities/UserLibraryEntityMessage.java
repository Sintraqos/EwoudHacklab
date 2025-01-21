package com.sintraqos.portfolioproject.userLibrary.entities;

import com.sintraqos.portfolioproject.statics.Message;
import com.sintraqos.portfolioproject.userLibrary.DAL.UserLibraryEntity;
import lombok.Getter;

import java.util.List;

@Getter
public class UserLibraryEntityMessage extends Message {
    private UserLibraryEntity entity;
    private List<UserLibraryEntity> entities;

    public UserLibraryEntityMessage(UserLibraryEntity entity, String message) {
        super(true, message);
        this.entity = entity;
    }

    public UserLibraryEntityMessage(List<UserLibraryEntity> entities) {
        super(true, "");
        this.entities = entities;
    }

    public UserLibraryEntityMessage(String message) {
        super(false, message);
    }
}
