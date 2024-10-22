package com.sintraqos.portfolioproject.Entities;

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
public class AccountLibrary {
    @Id
    @Column(name = "accountID", columnDefinition = "INT") // Specify type if needed
    private int accountID;

    @Column(name = "gameID", columnDefinition = "INT")
    private int gameID;

    @Column(name = "gamePlayTime", columnDefinition = "INT DEFAULT 0")
    private int gamePlayTime;

    @Column(name = "gameAquired", columnDefinition = "DATE")
    private Date gameAquired;

    @Column(name = "gameLastPlayed", columnDefinition = "DATE")
    private Date gameLastPlayed;
}