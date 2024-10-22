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

    @Column(name = "gameName", nullable = false, length = 100)
    private String gameName;

    @Column(name = "gameDescription")
    private String gameDescription;

    @Column(name = "gameDeveloper")
    private String gameDeveloper;

    @Column(name = "gamePublisher")
    private String gamePublisher;

    public GameEntity(String gamename, String gameDescription, String gameDeveloper, String gamePublisher)
    {
        this.gameName = gamename;
        this.gameDescription = gameDescription;
        this.gameDeveloper = gameDeveloper;
        this.gamePublisher = gamePublisher;
    }
}