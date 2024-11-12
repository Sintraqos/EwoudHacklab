package com.sintraqos.portfolioproject.Messages;

import com.sintraqos.portfolioproject.DTO.UserDTO;
import lombok.Getter;

@Getter
public class UserMessage extends Message {
    private UserDTO account;

    public UserMessage(UserDTO account, String message) {
        super(true, message);
        this.account = account;
    }

    public UserMessage(String message) {
        super(false, message);
    }
}
