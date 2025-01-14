package com.sintraqos.portfolioproject.User;

import com.sintraqos.portfolioproject.Game.Game;
import com.sintraqos.portfolioproject.Statics.Enums;
import com.sintraqos.portfolioproject.UserLibrary.UserLibrary;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Use for storing account data locally
 */
@Getter
public class User implements UserDetails {
    private int accountID = -1; // Default value for check if the database needs to assign a new ID
    private final String username;
    private String eMail;
    private String password;
    private UserLibrary userLibrary;
    private Enums.Role role= Enums.Role.USER;
    private final boolean isAccountNonExpired= true;
    private final boolean isAccountNonLocked= true;
    private final boolean isCredentialsNonExpired= true;
    private final boolean isEnabled= true;

    /**
     * Create a new account object from a DTO object
     *
     * @param userEntity the incoming Entity object
     */
    public User(UserEntity userEntity){
        this.accountID = userEntity.getAccountID();
        this.username = userEntity.getUsername();
        this.eMail = userEntity.getEMail();
        this.password = userEntity.getPassword();
        this.role = userEntity.getRole();
        this.userLibrary = new UserLibrary();
    }

    /**
     * Create a new account object from a DTO object
     *
     * @param userDTO the incoming DTO object
     */
    public User(UserDTO userDTO){
        this.accountID = userDTO.getAccountID();
        this.username = userDTO.getUsername();
        this.eMail = userDTO.getEMail();
        this.password = userDTO.getPassword();
        this.role = userDTO.getRole();
        this.userLibrary = new UserLibrary(userDTO.getUserLibrary());
    }

    /**
     * Create a new account object from an Entity object
     *
     * @param userEntity the incoming Entity object
     */
    public User(UserEntity userEntity, UserLibrary userLibrary){
        this.accountID = userEntity.getAccountID();
        this.username = userEntity.getUsername();
        this.eMail = userEntity.getEMail();
        this.password = userEntity.getPasswordHash();
        this.role = userEntity.getRole();
        this.userLibrary = userLibrary;
    }

    /**
     * Create a new account object without any games
     *
     * @param username the userName of the account
     * @param password the password of the account
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        userLibrary = new UserLibrary();
    }

    /**
     * Create a new account object without any games
     *
     * @param username the userName of the account
     * @param eMail    the e-mail of the account
     * @param password the password of the account
     */
    public User(String username, String eMail, String password) {
        this.username = username;
        this.eMail = eMail;
        this.password = password;
        this.userLibrary = new UserLibrary();
    }

    /**
     * When importing the account from external location.
     * Since it isn't important to get the password or email when logged in don't fill it in
     *
     * @param accountID   the ID of the user inside the database
     * @param username    the userName of the account
     * @param gameLibrary library the account has stored inside the database
     */
    public User(int accountID, String username, ArrayList<Game> gameLibrary) {
        this.accountID = accountID;
        this.username = username;
        this.userLibrary = new UserLibrary(gameLibrary);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public String toString()
    {
        return "%s%s".formatted(username, userLibrary);
    }
}
