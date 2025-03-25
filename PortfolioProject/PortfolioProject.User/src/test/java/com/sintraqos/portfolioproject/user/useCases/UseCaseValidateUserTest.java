package com.sintraqos.portfolioproject.user.useCases;

import com.sintraqos.portfolioproject.shared.CensorService;
import com.sintraqos.portfolioproject.shared.Errors;
import com.sintraqos.portfolioproject.user.DAL.UserEntity;
import com.sintraqos.portfolioproject.user.DAL.UserRepository;
import com.sintraqos.portfolioproject.user.entities.UserMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.regex.Pattern;

import static org.mockito.Mockito.when;

class UseCaseValidateUserTest {
    @Mock
    UserRepository userRepository;

    @Mock
    CensorService censorService;

    @Mock
    PasswordEncoder passwordEncoder;

    String username = "TEST Username";
    String eMail = "valid@EMail.com";
//    String eMail = "invalidEMail";
    String password = "TEST P@$$w0rd";
//    String password = "TEST Password";
    String specialCharRegex = "[!@#$%^&*()\\-_=+\\[\\]{}]";
    String capitalRegex = "[A-Z]";
    String eMailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";

    int minLength = 2;  // Min length of a string
    int maxLength = 32; // Max length of a string
    boolean passwordContainSpecialChar = true;
    boolean passwordContainCapital = true;

    @BeforeEach
    void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void validateUsername() {

    }

    @Test
    void validateEMail() {

    }

    @Test
    void validatePassword() {

    }
}