package com.sintraqos.portfolioproject.Game;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

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
    @NotBlank(message = "Game name is mandatory.")
    private String gameName;

    @Column()
    @NotBlank(message = "Game description is mandatory.")
    private String gameDescription;

    @Column()
    @NotBlank(message = "Game developer is mandatory.")
    private String gameDeveloper;

    @Column()
    @NotBlank(message = "Game publisher is mandatory.")
    private String gamePublisher;

    public GameEntity(String gameName, String gameDescription, String gameDeveloper, String gamePublisher) {
        this.gameName = gameName;
        this.gameDescription = gameDescription;
        this.gameDeveloper = gameDeveloper;
        this.gamePublisher = gamePublisher;
    }
}