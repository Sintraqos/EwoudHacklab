package com.sintraqos.portfolioproject.ForumPost;

import java.sql.Date;

public class ForumPost {

    private final int forumPostID;
    private final int accountID;
    private final int gameID;
    private final String message;
    private Date messageDate;

    public ForumPost(int forumPostID, int accountID, int gameID, String message) {
        this.forumPostID = forumPostID;
        this.accountID = accountID;
        this.gameID = gameID;
        this.message = message;
    }

    public ForumPost(int forumPostID, int accountID, int gameID, String message, Date messageDate) {
        this.forumPostID = forumPostID;
        this.accountID = accountID;
        this.gameID = gameID;
        this.message = message;
    }

    @Override
    public String toString() {

        //TODO: Set the date and time in format of: [DD-MM-YYYY - HH:MM] - Username: 'Message'


        return "[%s - %s - %s]: %s".formatted(forumPostID, accountID, gameID, message);
    }
}
