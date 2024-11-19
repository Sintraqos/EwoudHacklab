package com.sintraqos.portfolioproject.ForumPost;

public class ForumPost {

    private final int forumPostID;
    private final int accountID;
    private final int gameID;
    private final String message;

    public ForumPost(int forumPostID, int accountID, int gameID, String message) {
        this.forumPostID = forumPostID;
        this.accountID = accountID;
        this.gameID = gameID;
        this.message = message;
    }

    @Override
    public String toString() {
        return "[%s - %s - %s]: %s".formatted(forumPostID, accountID, gameID, message);
    }
}
