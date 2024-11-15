package com.sintraqos.portfolioproject.Messages;

import lombok.Getter;

/**
 * Use for returning multiple variables at once instead of a singular one
 */
@Getter
public class Message {
    boolean isSuccessful;
    String message;

    /**
     * Create a new Message object
     *
     * @param isSuccessful if the action was successful
     * @param message the message the sender wishes to send back
     */
    public Message(boolean isSuccessful, String message) {
        this.isSuccessful = isSuccessful;
        this.message = message;
    }

    /**
     * Create a new Message object
     *
     * @param message the message the sender wishes to send back
     */
    public Message(String message) {
        this.message = message;
    }
}
