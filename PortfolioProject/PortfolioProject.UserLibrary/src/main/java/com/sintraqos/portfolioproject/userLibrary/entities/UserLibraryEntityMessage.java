package com.sintraqos.portfolioproject.userLibrary.entities;

import com.sintraqos.portfolioproject.userLibrary.DAL.UserLibraryEntity;
import lombok.Getter;

@Getter
public class UserLibraryEntityMessage {
    boolean isSuccessful;
    String message;

    private UserLibraryEntity entity;

    /**
     * Create a new Message object
     *
     * @param message the message the sender wishes to send back
     */
    public UserLibraryEntityMessage(String message) {
        isSuccessful = false;
        this.message = message;
    }
    public UserLibraryEntityMessage(UserLibraryEntity entity, String message) {
        isSuccessful = true;
        this.message = message;
        this.entity = entity;
    }
}
