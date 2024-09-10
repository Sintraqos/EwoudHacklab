package com.sintraqos.portfolioproject.entity.player;

import com.sintraqos.portfolioproject.entity.entity.EntityMaster;
import com.sintraqos.portfolioproject.statics.Enums;

public class PlayerMaster extends EntityMaster {

    private boolean playerIsMale;
    private Enums.playerClass playerClass;

    private int playerCurrentExp;

    public void SetPlayerName(String playerName) {
        entityName = playerName;
    }

    public int getPlayerCurrentExp() {
        return playerCurrentExp;
    }

    PlayerMaster() {
        entityName = "Sintraqos";
    }

    public PlayerMaster(boolean playerIsMale, Enums.playerClass playerClass) {
        entityName = "";
        this.playerIsMale = playerIsMale;
        this.playerClass = playerClass;
        playerCurrentExp = 0;
    }

    @Override
    public String toString() {
        return getEntityName();
    }
}
