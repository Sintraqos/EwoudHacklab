package com.sintraqos.portfolioprojectAPI.user.entities;

import com.sintraqos.portfolioprojectAPI.user.DAL.UserEntity;
import com.sintraqos.portfolioprojectAPI.user.DTO.UserDTO;
import lombok.Getter;

import java.util.Comparator;
import java.util.List;

@Getter
public class UserMessage {    boolean isSuccessful;
    String message;
    private UserDTO userDTO;
    private UserEntity userEntity;
    private List<UserEntity> entities ;

    /**
     * Create a new Message object
     *
     * @param isSuccessful if the action was successful
     * @param message the message the sender wishes to send back
     */
    public UserMessage(boolean isSuccessful, String message) {
        this.isSuccessful = isSuccessful;
        this.message = message;
    }

    /**
     * Create a new Message object
     *
     * @param message the message the sender wishes to send back
     */
    public UserMessage(String message) {
        this.isSuccessful = false;
        this.message = message;
    }

    public UserMessage(UserDTO userDTO, UserEntity userEntity, String message) {
        this.isSuccessful = true;
        this.message = message;
        this.userDTO = userDTO;
        this.userEntity = userEntity;
    }

    public UserMessage(UserEntity userEntity, String message) {
        this.isSuccessful = true;
        this.message = message;
        this.userEntity = userEntity;
        this.userDTO = new UserDTO(userEntity);
    }

    public UserMessage(List<UserEntity> entities, String message) {
        this.isSuccessful = true;
        this.message = message;
        entities.sort(Comparator.comparing(UserEntity::getUsername, String.CASE_INSENSITIVE_ORDER));  // Sort the incoming userEntities by their username
        this.entities = entities;
    }
}
