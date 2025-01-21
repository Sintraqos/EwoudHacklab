package com.sintraqos.portfolioproject.forumPost.DTO;

import lombok.Getter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Forum Post DTO, use for transfer of message data
 */
@Getter
public class ForumPostDTO {

    private int forumPostID;

    private final int accountID;
    private String username;

    private final int gameID;
    private String gameName;

    private final String message;
    private Timestamp postDate;

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
        this.postDate = postDate;
    }

    /**
     * Create new DTO
     *
     * @param forumPostID the ID of the forumPost
     * @param accountID the ID of the account
     * @param username the username of the account
     * @param gameID the ID of the game
     * @param gameName the name of the game
     * @param message the message the user left
     */
    public ForumPostDTO(int forumPostID, int accountID,String username, int gameID,String gameName, String message) {
        this.forumPostID = forumPostID;
        this.accountID = accountID;
        this.username = username;
        this.gameID = gameID;
        this.gameName = gameName;
        this.message = message;
    }

    /**
     * Create new DTO
     *
     * @param forumPostID the ID of the forumPost
     * @param accountID the ID of the account
     * @param username the username of the account
     * @param gameID the ID of the game
     * @param gameName the name of the game
     * @param message the message the user left
     * @param postDate the date and time of the message
     */
    public ForumPostDTO(int forumPostID, int accountID, String username, int gameID, String gameName, String message, Timestamp postDate) {
        this.forumPostID = forumPostID;
        this.accountID = accountID;
        this.username = username;
        this.gameID = gameID;
        this.gameName = gameName;
        this.message = message;
        this.postDate = postDate;
    }

    @Override
    public String toString() {
        // Return the string as: '[DD-MM-YYYY - HH:MM] Username: Message'
        return "[%s] %s: %s".formatted(getTimestamp(), username, message);
    }

    public String getTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy - HH:mm");
        return sdf.format(postDate);
    }
}
