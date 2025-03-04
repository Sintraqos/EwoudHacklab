package com.sintraqos.portfolioproject.forum.forumPost.DAL;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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

    @Column(nullable = false, length = 8192)
    private String message;

    @Column(name = "postDate", columnDefinition = "TIMESTAMP")
    private Timestamp postDate;

    @PrePersist
    public void prePersist() {
        if (postDate == null) {
            postDate = Timestamp.valueOf(LocalDateTime.now());
        }
    }

    public ForumPostEntity(int accountID, int gameID, String message) {
        this.accountID = accountID;
        this.gameID = gameID;
        this.message = message;
    }
}