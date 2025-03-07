package com.sintraqos.portfolioproject.userLibrary.DAL;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Account Library Entity Object, use for creating new Database Tables, and for storing the data from the database
 */
@Getter
@NoArgsConstructor
@Entity
@Table(name = "accountlibrary")
public class UserLibraryEntity {
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
    @Temporal(TemporalType.DATE)
    private Date gameAcquired;

    @Column(name = "gameLastPlayed", columnDefinition = "DATE")
    private Date gameLastPlayed;

    @PrePersist
    public void prePersist() {
        if (gameAcquired == null) {
            gameAcquired = Date.valueOf(LocalDate.now());
        }
    }

    public UserLibraryEntity(int accountID, int gameID) {
        this.accountID = accountID;
        this.gameID = gameID;
    }

    @Override
    public String toString(){
        return "%s - %s".formatted(accountID, gameID);
    }
}