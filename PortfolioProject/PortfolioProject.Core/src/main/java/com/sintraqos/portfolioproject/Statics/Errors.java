package com.sintraqos.portfolioproject.Statics;

public class Errors {
    // Username
    public static final String USERNAME_ALREADY_IN_USE = "Username is already in use";
    public static final String USERNAME_INVALID_LENGTH = "Username length must be between 6 and 16 characters";
    public static final String USERNAME_MATCH = "Username the same as the previous username";

    // E-Mail
    public static final String EMAIL_ALREADY_IN_USE = "E-Mail address is already in use";

    // Password
    public static final String PASSWORD_INVALID_LENGTH = "Password length must be between 6 and 16 characters";
    public static final String PASSWORD_MISMATCH = "Password mismatch";
    public static final String PASSWORD_INCORRECT = "Password incorrect";
    public static final String PASSWORD_MATCH = "Password the same as the previous password";

    // Database
    public static final String GAME_EXISTS = "Game with name: '%s' already exists";
    public static final String FIND_GAME_NAME_FAILED = "Couldn't find game by name: '%s'";
    public static final String FIND_GAME_ID_FAILED = "Couldn't find game by ID: '%s'";
    public static final String FIND_ACCOUNT_NAME_FAILED = "Couldn't find account by name: '%s'";
    public static final String FIND_ACCOUNT_ID_FAILED = "Couldn't find account by ID: '%s'";
    public static final String ACCOUNT_CONTAINS_GAME = "Game with ID: '%s' already in account";
    public static final String FORUM_GAME_ID_FAILED = "Failed to retrieve forum posts for game with ID: '%s'";
    public static final String FORUM_ACCOUNT_ID_FAILED = "Failed to retrieve forum posts for account with ID: '%s'";

    // Various
    public static final String NUMERIC_VALUE_OUT_OF_RANGE = "Invalid numeric value: '%s'";
    public static final String NUMERIC_VALUE_TYPE = "Value: '%s' not numeric";
}
