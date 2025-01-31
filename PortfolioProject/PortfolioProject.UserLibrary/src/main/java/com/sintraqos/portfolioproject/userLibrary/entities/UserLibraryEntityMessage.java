package com.sintraqos.portfolioproject.userLibrary.entities;

import com.sintraqos.portfolioproject.userLibrary.DAL.UserLibraryEntity;
import lombok.Getter;

import java.util.List;

@Getter
public class UserLibraryEntityMessage {
    boolean isSuccessful;
    String message;

    private UserLibraryEntity entity;
    private List<UserLibraryEntity> entities;

    /**
     * Create a new Message object
     *
     * @param isSuccessful if the action was successful
     * @param message the message the sender wishes to send back
     */
    public UserLibraryEntityMessage(boolean isSuccessful, String message) {
        this.isSuccessful = isSuccessful;
        this.message = message;
    }

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

    public UserLibraryEntityMessage(List<UserLibraryEntity> entities) {
        isSuccessful = true;
        this.message = "";
        this.entities = entities;
    }
}
