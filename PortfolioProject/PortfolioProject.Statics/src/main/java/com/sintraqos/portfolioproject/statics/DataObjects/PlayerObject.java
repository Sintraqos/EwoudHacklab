package com.sintraqos.portfolioproject.statics.DataObjects;

import java.util.ArrayList;

public class PlayerObject  implements java.io.Serializable {

    // Player Info
    String playerName;
    boolean playerIsMale;

    // Exp And Level
    int playerCurrentExp;
    int playerCurrentLevel;

    // Health
    int playerCurrentHealth, playerMaxHealth;
    boolean playerIsDead;
    // Force
    int playerCurrentForce, playerMaxForce;

    // Alignment
    int currentAlignment;

    // Current Followers
    ArrayList<Integer> activeCompanions;

    public PlayerObject(
            String playerName,
            boolean playerIsMale,
            int playerCurrentExp,
            int playerCurrentLevel,
            int playerCurrentHealth,
            int playerMaxHealth,
            boolean playerIsDead,
            int playerCurrentForce,
            int playerMaxForce,
            ArrayList<Integer> activeCompanions
    ) {
        this.playerName = playerName;
        this.playerIsMale = playerIsMale;
        this.playerCurrentExp = playerCurrentExp;
        this.playerCurrentLevel = playerCurrentLevel;
        this.playerCurrentHealth = playerCurrentHealth;
        this.playerMaxHealth = playerMaxHealth;
        this.playerIsDead = playerIsDead;
        this.playerCurrentForce = playerCurrentForce;
        this.playerMaxForce = playerMaxForce;
        this.activeCompanions = activeCompanions;
    }
}
