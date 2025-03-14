package com.sintraqos.portfolioproject.user.useCases;

import com.sintraqos.portfolioproject.user.DAL.UserEntity;
import com.sintraqos.portfolioproject.user.DAL.UserRepository;
import com.sintraqos.portfolioproject.user.DTO.UserDTO;
import com.sintraqos.portfolioproject.user.entities.UserMessage;
import com.sintraqos.portfolioproject.user.statics.Enums;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

class UseCaseRegisterAccountTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UseCaseValidateUser validateUser;

    String username = "TEST Username";
    String eMail = "TEST E-Mail";
    String password = "TEST Password";

    @BeforeEach
    void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerAccount() {
        // Mock the behavior of getAccount() to return a successful UserMessage
        UserMessage mockUserMessage = new UserMessage(Instancio.create(UserDTO.class), "Account validated");
        when(validateUser.validateUser(username, eMail, password)).thenReturn(mockUserMessage);

        System.out.printf("Attempting to register new account with username: '%s'%n", username);
        // Check if the user is valid
        if (!mockUserMessage.isSuccessful()) {
            System.out.println(mockUserMessage.getMessage());

            return;
        }

        // Create and save the new user
        UserEntity userEntity = new UserEntity(username, eMail, password, Enums.Role.USER);
        userEntity.setAccountNonExpired(true);
        userEntity.setAccountNonLocked(true);
        userEntity.setCredentialsNonExpired(true);
        userEntity.setEnabled(true);
        userRepository.save(userEntity);

        // Cast the accountEntity to an AccountDTO object for transfer
        String message = "Created new account: '%s'".formatted(username);
        System.out.println(message);
    }
}