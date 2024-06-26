package com.sintraqos.portfolioproject.entity.player;

import com.sintraqos.portfolioproject.entity.entity.EntityMaster;

public class PlayerMaster extends EntityMaster {

    private int characterCurrentExp;

    public int getCharacterCurrentExp() {
        return characterCurrentExp;
    }

    @Override
    public String toString() {
        return getEntityName();
    }
}
