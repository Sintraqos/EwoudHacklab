package com.sintraqos.portfolioproject.game.DAL;

// Project components
import com.sintraqos.portfolioproject.game.entities.Game;

// External components
import lombok.*;
import jakarta.persistence.*;

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
    private int gameID = -1;

    @Column(nullable = false, length = 100)
    private String gameName;

    @Column()
    private String gameDescription;

    @Column()
    private String gameDeveloper;

    @Column()
    private String gamePublisher;

    public GameEntity(Game game) {
        this.gameName = game.getGameName();
        this.gameDescription = game.getGameDescription();
        this.gameDeveloper = game.getGameDeveloper();
        this.gamePublisher = game.getGamePublisher();
    }
}