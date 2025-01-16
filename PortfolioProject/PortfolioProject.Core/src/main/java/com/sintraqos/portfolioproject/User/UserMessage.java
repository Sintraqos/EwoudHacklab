package com.sintraqos.portfolioproject.User;

import com.sintraqos.portfolioproject.Messages.Message;
import lombok.Getter;

import java.util.Comparator;
import java.util.List;

@Getter
public class UserMessage extends Message {
    private UserDTO userDTO;
    private UserEntity userEntity;
    private List<UserEntity> entities ;

    public UserMessage(UserDTO userDTO, UserEntity userEntity, String message) {
        super(true, message);
        this.userDTO = userDTO;
        this.userEntity = userEntity;
    }

    public UserMessage(UserEntity userEntity, String message) {
        super(true, message);
        this.userEntity = userEntity;
        this.userDTO = new UserDTO(userEntity);
    }

    public UserMessage(List<UserEntity> entities, String message) {
        super(true, message);
        entities.sort(Comparator.comparing(UserEntity::getUsername, String.CASE_INSENSITIVE_ORDER));  // Sort the incoming userEntities by their username
        this.entities = entities;
    }

    public UserMessage(String message) {
        super(false, message);
    }
}
