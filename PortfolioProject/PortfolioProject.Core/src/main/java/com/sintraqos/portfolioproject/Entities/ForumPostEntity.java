package com.sintraqos.portfolioproject.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Message Entity Object, use for creating new Database Tables, and for storing the data from the database
 */
@Getter
@NoArgsConstructor
@Entity
@Table(name = "forumPosts")
public class ForumPostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int forumPostID;

    @Column(columnDefinition = "INT")
    private int accountID;

    @Column(columnDefinition = "INT")
    private int gameID;

    @Column(nullable = false, length = 1024)
    private String message;

    public ForumPostEntity(int accountID, int gameID, String message) {
        this.accountID = accountID;
        this.gameID = gameID;
        this.message = message;
    }
    
    public ForumPostEntity(int forumPostID, int accountID, int gameID, String message) {
        this.forumPostID = forumPostID;
        this.accountID = accountID;
        this.gameID = gameID;
        this.message = message;
    }
}