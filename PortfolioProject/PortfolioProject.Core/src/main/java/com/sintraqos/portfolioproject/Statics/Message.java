package com.sintraqos.portfolioproject.Statics;

import lombok.Getter;

import java.util.ArrayList;

/**
 * Use for returning multiple variables at once instead of a singular one
 */
@Getter
public class Message {
    boolean isSuccessful;
    String message;

    ArrayList<String> stringVariables;
    ArrayList<Integer> intVariables;

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
