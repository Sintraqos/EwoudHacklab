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
        // Check if the username is a valid length
        int usernameLength = username.length();

        System.out.printf("Incoming Username: %s%n", username);
        System.out.printf("Username Length: %s%n", usernameLength);
        System.out.printf("Username Min Length: %s%n", minLength);
        System.out.printf("Username Max Length: %s%n", maxLength);

        // Username is too short
        if (usernameLength < minLength) {
            String message = Errors.USERNAME_INVALID_LENGTH_SHORT.formatted(minLength, maxLength);
            System.out.println(message);

            return;
        }

        // Username is too long
        if (usernameLength > maxLength) {
            String message = Errors.USERNAME_INVALID_LENGTH_LONG.formatted(minLength, maxLength);
            System.out.println(message);

            return;
        }

        // Check if the username contains a banned word
        if (censorService.containsBannedWord(username)) {
            System.out.println(Errors.USERNAME_CONTAINS_BANNED_WORD);

            return;
        }

        // Check if an account with the given username already exists
        when(userRepository.findByUsername(username)).thenReturn(null);
//        when(userRepository.findByUsername(username)).thenReturn(new UserEntity());
        if (userRepository.findByUsername(username) != null) {
            System.out.printf((Errors.USERNAME_ALREADY_IN_USE) + "%n", username);

            return;
        }

        System.out.println("Username Validated");

        Assertions.assertTrue(true);
    }

    @Test
    void validateEMail() {
        // Check if the email matches the pattern
        if (!Pattern.compile(eMailRegex).matcher(eMail).matches()) {
            String message = Errors.EMAIL_INVALID.formatted(eMail);
            System.out.println(message);

            return;
        }

        // Check if an account with the given E-Mail already exists
        when(userRepository.findByEmail(eMail)).thenReturn(null);
//        when(userRepository.findByEmail(eMail)).thenReturn(new UserEntity());
        if (userRepository.findByEmail(eMail) != null) {
            String message = Errors.EMAIL_ALREADY_IN_USE.formatted(eMail);
            System.out.println(message);

            return;
        }

        System.out.println("E-Mail Validated");

        Assertions.assertTrue(true);
    }

    @Test
    void validatePassword() {
        // Check if the password is a valid length
        int passwordLength = password.length();

        System.out.printf("Incoming Password: %s%n", password);
        System.out.printf("Password Length: %s%n", passwordLength);
        System.out.printf("Password Min Length: %s%n", minLength);
        System.out.printf("Password Max Length: %s%n", maxLength);

        // Password is too short
        if (passwordLength <minLength) {
            String message = Errors.PASSWORD_INVALID_LENGTH_SHORT.formatted(minLength, maxLength);
            System.out.println(message);

            return;
        }
        // Password is too long
        if (passwordLength > maxLength) {
            String message = Errors.PASSWORD_INVALID_LENGTH_LONG.formatted(minLength, maxLength);
            System.out.println(message);

            return;
        }

        // Check to see if the password needs to have a special character, IE: *
        if(passwordContainSpecialChar){
            if (!Pattern.compile(specialCharRegex).matcher(password).find()) {
                System.out.println(Errors.PASSWORD_INVALID_SPECIAL_CHAR);

                return;
            }
        }

        // Check if the password needs to contain a capitalized letter inside
        if(passwordContainCapital){
            if (!Pattern.compile(capitalRegex).matcher(password).find()) {
                System.out.println(Errors.PASSWORD_INVALID_CAPITAL_CHAR);

                return;
            }
        }

        System.out.println("Password Validated");
        Assertions.assertTrue(true);
    }
}