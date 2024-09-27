package com.sintraqos.portfolioproject.Statics;

import lombok.Getter;

public class Message {
    @Getter
    boolean isSuccessful;
    @Getter
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
}
