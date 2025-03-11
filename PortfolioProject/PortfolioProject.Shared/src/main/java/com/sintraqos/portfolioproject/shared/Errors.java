package com.sintraqos.portfolioproject.shared;

public class Errors {

    //region Database

    public static final String AUTH_FAILED = "Authentication Failed";
    /**
     * Formatted String: Game Name
     */
    public static final String GAME_EXISTS = "Game with name: '%s' already exists";
    /**
     * Formatted String: Game Name
     */
    public static final String FIND_GAME_NAME_FAILED = "Couldn't find game by name: '%s'";
    /**
     * Formatted String: Game ID
     */
    public static final String FIND_GAME_ID_FAILED = "Couldn't find game by ID: '%s'";
    /**
     * Formatted String: username
     */
    public static final String FIND_USER_NAME_FAILED = "Couldn't find account by name: '%s'";
    /**
     * Formatted String: user ID
     */
    public static final String FIND_USER_ID_FAILED = "Couldn't find account by name: '%s'";
    /**
     * Formatted String: external database address
     */
    public static final String API_CONNECTION_FAILED = "Failed to connect to the external database: '%s'";

    //endregion

    //region User

    public static final String USER_NOT_LOGGED_IN = "You must be logged in to access your account.";
    /**
     * Formatted String: username
     */
    public static final String USER_BANNED = "Account with username: '%s' is banned";
    /**
     * Formatted String: Game ID
     */
    public static final String USER_CONTAINS_GAME = "Game with ID: '%s' already in account";

    // Username
    /**
     * Formatted String: username
     */
    public static final String USERNAME_ALREADY_IN_USE = "Username: '%s' is already in use";
    /**
     * Formatted String: minLength, maxLength
     */
    public static final String USERNAME_INVALID_LENGTH_SHORT = "Username is too short, it must be between %s and %s characters";
    /**
     * Formatted String: minLength, maxLength
     */
    public static final String USERNAME_INVALID_LENGTH_LONG = "Username is too long, it must be between %s and %s characters";
    public static final String USERNAME_MATCH = "Username the same as the previous username";
    public static final String USERNAME_CONTAINS_BANNED_WORD = "Username contains a banned word";

    // E-Mail
    /**
     * Formatted String: E-Mail address
     */
    public static final String EMAIL_ALREADY_IN_USE = "E-Mail address: '%s' is already in use";
    public static final String EMAIL_INVALID = "E-Mail address: '%s' is invalid";

    // Password
    public static final String PASSWORD_MISMATCH = "Password mismatch";
    public static final String PASSWORD_INCORRECT = "Password incorrect";
    public static final String PASSWORD_MATCH = "Password the same as the previous password";
    /**
     * Formatted String: minLength, maxLength
     */
    public static final String PASSWORD_INVALID_LENGTH_SHORT = "Password is too short, it must be between %s and %s characters";
    /**
     * Formatted String: minLength, maxLength
     */
    public static final String PASSWORD_INVALID_LENGTH_LONG = "Password is too long, it must be between %s and %s characters";
    public static final String PASSWORD_INVALID_SPECIAL_CHAR = "Password requires a special character";
    public static final String PASSWORD_INVALID_CAPITAL_CHAR = "Password requires a capitalized character";

    //endregion

    //region Forum

    /**
     * Formatted String: minLength, maxLength
     */
    public static final String FORUM_INVALID_LENGTH_SHORT = "Message is too short, it must be between %s and %s characters";
    /**
     * Formatted String: minLength, maxLength
     */
    public static final String FORUM_INVALID_LENGTH_LONG = "Message is too long, it must be between %s and %s characters";
    /**
     * Formatted String: Game ID
     */
    public static final String FORUM_GAME_ID_FAILED = "Failed to retrieve forum posts for game with ID: '%s'";
    /**
     * Formatted String: user ID
     */
    public static final String FORUM_USER_ID_FAILED = "Failed to retrieve forum posts for account with ID: '%s'";

    //endregion

    //region Various

    public static final String NUMERIC_VALUE_TYPE = "Value: '%s' not numeric";

    //endregion
}
