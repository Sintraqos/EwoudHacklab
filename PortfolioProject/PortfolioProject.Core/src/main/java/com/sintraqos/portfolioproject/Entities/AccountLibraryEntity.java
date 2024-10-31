package com.sintraqos.portfolioproject.Entities;

import com.sintraqos.portfolioproject.DTO.GameDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.sql.Date;

/**
 * Account Library Entity Object, use for creating new Database Tables, and for storing the data from the database
 */
@Getter
@NoArgsConstructor
@Entity
@Table(name = "accountlibrary")
public class AccountLibraryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int libraryID;

    @Column(columnDefinition = "INT")
    private int accountID;

    @Column(columnDefinition = "INT")
    private int gameID;

    @Column(name = "gamePlayTime", columnDefinition = "INT DEFAULT 0")
    private int gamePlayTime;

    @Column(name = "gameAquired", columnDefinition = "DATE")
    private Date gameAcquired;

    @Column(name = "gameLastPlayed", columnDefinition = "DATE")
    private Date gameLastPlayed;

    public AccountLibraryEntity(int accountID, GameDTO gameDTO) {
        this.accountID = accountID;
        this.gameID = gameDTO.getGameID();
        this.gamePlayTime = gameDTO.getGamePlayTime().getTotalMinutes();
        this.gameAcquired = (Date) gameDTO.getGameAcquired();
        this.gameLastPlayed = (Date) gameDTO.getGameLastPlayed();
    }

    public AccountLibraryEntity(int accountID, GameEntity gameDTO) {
        this.accountID = accountID;
        this.gameID = gameDTO.getGameID();
    }

    public AccountLibraryEntity(int accountID, int gameID) {
        this.accountID = accountID;
        this.gameID = gameID;
    }

    @Override
    public String toString(){
        return "%s - %s".formatted(accountID, gameID);
    }
}