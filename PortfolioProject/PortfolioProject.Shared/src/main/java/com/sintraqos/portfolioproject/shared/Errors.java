package com.sintraqos.portfolioproject.shared;

public class Errors {

    // Username
    public static final String USERNAME_ALREADY_IN_USE = "Username: '%s' is already in use";
    public static final String USERNAME_INVALID_LENGTH_SHORT = "Username is too short, it must be between %s and %s characters";
    public static final String USERNAME_INVALID_LENGTH_LONG = "Username is too long, it must be between %s and %s characters";
    public static final String USERNAME_MATCH = "Username the same as the previous username";
    public static final String USERNAME_CONTAINS_BANNED_WORD = "Username contains a banned word";

    // E-Mail
    public static final String EMAIL_ALREADY_IN_USE = "E-Mail address: '%s' is already in use";
    public static final String EMAIL_INVALID = "E-Mail address is invalid";

    // Password
    public static final String PASSWORD_MISMATCH = "Password mismatch";
    public static final String PASSWORD_INCORRECT = "Password incorrect";
    public static final String PASSWORD_MATCH = "Password the same as the previous password";
    public static final String PASSWORD_INVALID_LENGTH_SHORT = "Password is too short, it must be between %s and %s characters";
    public static final String PASSWORD_INVALID_LENGTH_LONG = "Password is too long, it must be between %s and %s characters";
    public static final String PASSWORD_INVALID_SPECIAL_CHAR = "Password requires a special character";
    public static final String PASSWORD_INVALID_CAPITAL_CHAR = "Password requires a capitalized character";

    // Database
    public static final String AUTH_FAILED = "Authentication Failed";
    public static final String GAME_EXISTS = "Game with name: '%s' already exists";
    public static final String FIND_GAME_NAME_FAILED = "Couldn't find game by name: '%s'";
    public static final String FIND_GAME_ID_FAILED = "Couldn't find game by ID: '%s'";
    public static final String FIND_ACCOUNT_NAME_FAILED = "Couldn't find account by name: '%s'";

    // Account
    public static final String ACCOUNT_NOT_LOGGED_IN = "You must be logged in to access your account.";
    public static final String ACCOUNT_BANNED = "Account with username: '%s' is banned";
    public static final String ACCOUNT_CONTAINS_GAME = "Game with ID: '%s' already in account";

    // Forum
    public static final String FORUM_INVALID_LENGTH_SHORT = "Message is too short, it must be between %s and %s characters";
    public static final String FORUM_INVALID_LENGTH_LONG = "Message is too long, it must be between %s and %s characters";
    public static final String FORUM_GAME_ID_FAILED = "Failed to retrieve forum posts for game with ID: '%s'";
    public static final String FORUM_ACCOUNT_ID_FAILED = "Failed to retrieve forum posts for account with ID: '%s'";

    // Various
    public static final String NUMERIC_VALUE_TYPE = "Value: '%s' not numeric";
}
