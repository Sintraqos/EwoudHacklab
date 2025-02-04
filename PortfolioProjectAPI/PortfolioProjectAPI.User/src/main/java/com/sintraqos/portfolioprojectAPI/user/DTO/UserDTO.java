package com.sintraqos.portfolioprojectAPI.user.DTO;

import com.sintraqos.portfolioprojectAPI.user.DAL.UserEntity;
import com.sintraqos.portfolioprojectAPI.user.statics.Enums;
import lombok.Getter;

/**
 * Account DTO, use for transfer of account data
 */
@Getter
public class UserDTO {
    private final int accountID;
    private final String username;
    private final String password;
    private Enums.Role role;

    /**
     * Create a new AccountDTO object based on AccountEntity
     *
     * @param user the AccountEntity object retrieved from the database
     */
    public UserDTO(UserEntity user) {
        this.accountID = user.getAccountID();
        this.username = user.getUsername();
        this.password = user.getPasswordHash();
        this.role = user.getRole();
    }
}
