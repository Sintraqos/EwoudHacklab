package com.sintraqos.portfolioproject.DTO;

import lombok.Getter;

/**
 * Forum Post DTO, use for transfer of message data
 */
@Getter
public class ForumPostDTO {

    private int forumPostID;

    private final int accountID;
    private String accountUsername;

    private final int gameID;
    private String gameName;

    private final String message;

    /**
     * Create new DTO
     *
     * @param accountID the ID of the account
     * @param gameID the ID of the game
     * @param message the message the user left
     */
    public ForumPostDTO(int accountID,int gameID, String message) {
        this.accountID = accountID;
        this.gameID = gameID;
        this.message = message;
    }


    /**
     * Create new DTO
     *
     * @param forumPostID the ID of the forumPost
     * @param accountID the ID of the account
     * @param accountUsername the username of the account
     * @param gameID the ID of the game
     * @param gameName the name of the game
     * @param message the message the user left
     */
    public ForumPostDTO(int forumPostID, int accountID,String accountUsername, int gameID,String gameName, String message) {
        this.forumPostID = forumPostID;
        this.accountID = accountID;
        this.accountUsername = accountUsername;
        this.gameID = gameID;
        this.gameName = gameName;
        this.message = message;
    }

    @Override
    public String toString() {
        return "[%s - %s - %s]: %s".formatted(forumPostID, accountID, gameID, message);
    }
}
