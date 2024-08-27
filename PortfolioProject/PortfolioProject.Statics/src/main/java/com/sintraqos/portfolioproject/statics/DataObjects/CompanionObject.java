package com.sintraqos.portfolioproject.statics.DataObjects;

public class CompanionObject implements java.io.Serializable {
    int companionID;
    String companionName;
    int companionCurrentExp;
    int companionCurrentLevel;

    // Health
    int companionCurrentHealth, companionMaxHealth;
    boolean companionIsDead;
    // Force
    int companionCurrentForce, companionMaxForce;

    // Dialogue
    int companionCurrentInfluence;

    public CompanionObject(
            int companionID,
            String companionName,
            int companionCurrentExp,
            int companionCurrentLevel,
            int companionCurrentHealth,
            int companionMaxHealth,
            boolean companionIsDead,
            int companionCurrentForce,
            int companionMaxForce,
            int companionCurrentInfluence
    ) {
        this.companionID = companionID;
        this.companionName = companionName;
        this.companionCurrentExp = companionCurrentExp;
        this.companionCurrentLevel = companionCurrentLevel;
        this.companionCurrentHealth = companionCurrentHealth;
        this.companionMaxHealth = companionMaxHealth;
        this.companionIsDead = companionIsDead;
        this.companionCurrentForce = companionCurrentForce;
        this.companionMaxForce = companionMaxForce;
        this.companionCurrentInfluence = companionCurrentInfluence;
    }
}
