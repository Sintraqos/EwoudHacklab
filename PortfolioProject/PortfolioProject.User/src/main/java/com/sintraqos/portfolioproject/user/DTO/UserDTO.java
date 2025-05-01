package com.sintraqos.portfolioproject.user.DTO;

// Project components
import com.sintraqos.portfolioproject.user.DAL.UserEntity;
import com.sintraqos.portfolioproject.user.statics.Enums;
import com.sintraqos.portfolioproject.userLibrary.DTO.UserLibraryDTO;

// External components
import lombok.Getter;

/**
 * Account DTO, use for transfer of account data
 */
@Getter
public class UserDTO {
    private final int accountID;
    private final String username;
    private final String eMail;
    private final String password;
    private final UserLibraryDTO userLibrary;
    private final Enums.Role role;

    public UserDTO()
    {
        this.accountID = 0;
        this.username = "";
        this.eMail = "";
        this.password = "";
        userLibrary = new UserLibraryDTO();
        role = Enums.Role.USER;
    }

    /**
     * Create a new AccountDTO object based on AccountEntity
     *
     * @param user the AccountEntity object retrieved from the database
     */
    public UserDTO(UserEntity user) {
        this.accountID = user.getAccountID();
        this.username = user.getUsername();
        this.eMail = user.getEmail();
        this.password = user.getPasswordHash();
        this.userLibrary = new UserLibraryDTO();
        this.role = user.getRole();
    }

    /**
     * Create a new AccountDTO object based on AccountEntity
     *
     * @param user the AccountEntity object retrieved from the database
     * @param userLibrary the Library retrieved from the database
     */
    public UserDTO(UserEntity user, UserLibraryDTO userLibrary) {
        this.accountID = user.getAccountID();
        this.username = user.getUsername();
        this.eMail = user.getEmail();
        this.password = user.getPasswordHash();
        this.userLibrary = userLibrary;
        this.role = user.getRole();
    }
}
