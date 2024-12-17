package com.sintraqos.portfolioproject.Messages;

import com.sintraqos.portfolioproject.DTO.UserDTO;
import com.sintraqos.portfolioproject.Entities.UserEntity;
import lombok.Getter;

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
        this.entities = entities;
    }

    public UserMessage(String message) {
        super(false, message);
    }
}
