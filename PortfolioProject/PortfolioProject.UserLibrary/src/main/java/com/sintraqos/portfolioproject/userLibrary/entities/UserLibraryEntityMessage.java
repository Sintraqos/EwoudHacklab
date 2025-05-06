package com.sintraqos.portfolioproject.userLibrary.entities;

// Project components
import com.sintraqos.portfolioproject.userLibrary.DAL.UserLibraryEntity;

// External components
import lombok.Getter;

@Getter
public class UserLibraryEntityMessage {
    boolean isSuccessful;
    String message;

    private UserLibraryEntity entity;

    /**
     * Create a new Message object, always returns as false
     *
     * @param message the message the sender wishes to send back
     */
    public UserLibraryEntityMessage(String message) {
        isSuccessful = false;
        this.message = message;
    }

    /**
     * Create a new Message object
     *
     * @param message the message the sender wishes to send back
     */
    public UserLibraryEntityMessage(boolean isSuccessful, String message){
        this.isSuccessful = isSuccessful;
        this.message = message;
    }

    /**
     * Create a new Message object, always returns as true
     *
     * @param message the message the sender wishes to send back
     */
    public UserLibraryEntityMessage(UserLibraryEntity entity, String message) {
        isSuccessful = true;
        this.message = message;
        this.entity = entity;
    }
}
