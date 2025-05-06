package com.sintraqos.portfolioproject.user.entities;

// Project components
import com.sintraqos.portfolioproject.user.DAL.UserEntity;
import com.sintraqos.portfolioproject.user.DTO.UserDTO;

// External components
import lombok.Getter;

// Java components
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
     * Create a new Message object, always returns as false
     *
     * @param message the message the sender wishes to send back
     */
    public UserMessage(String message) {
        this.isSuccessful = false;
        this.message = message;
    }

    /**
     * Create a new Message object containing a UserDTO, always returns as true
     *
     * @param message the message the sender wishes to send back
     */
    public UserMessage(UserDTO userDTO, String message) {
        this.isSuccessful = true;
        this.message = message;
        this.userDTO = userDTO;
        this.userEntity = new UserEntity(userDTO);
    }

    /**
     * Create a new Message object containing a UserEntity, always returns as true
     *
     * @param message the message the sender wishes to send back
     */
    public UserMessage(UserEntity userEntity, String message) {
        this.isSuccessful = true;
        this.message = message;
        this.userEntity = userEntity;
        this.userDTO = new UserDTO(userEntity);
    }

    /**
     * Create a new Message object containing a list of UserEntity, always returns as true
     *
     * @param message the message the sender wishes to send back
     */
    public UserMessage(List<UserEntity> entities, String message) {
        this.isSuccessful = true;
        this.message = message;
        entities.sort(Comparator.comparing(UserEntity::getUsername, String.CASE_INSENSITIVE_ORDER));  // Sort the incoming userEntities by their username
        this.entities = entities;
    }
}
