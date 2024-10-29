package com.sintraqos.portfolioproject.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Game Entity Object, use for creating new Database Tables, and for storing the data from the database
 */
@Getter
@NoArgsConstructor
@Entity
@Table(name = "games")
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gameID;

    @Column(nullable = false, length = 100)
    private String gameName;

    @Column()
    private String gameDescription;

    @Column()
    private String gameDeveloper;

    @Column()
    private String gamePublisher;

    public GameEntity(String gamename, String gameDescription, String gameDeveloper, String gamePublisher)
    {
        this.gameName = gamename;
        this.gameDescription = gameDescription;
        this.gameDeveloper = gameDeveloper;
        this.gamePublisher = gamePublisher;
    }
}