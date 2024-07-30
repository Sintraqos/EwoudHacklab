package com.sintraqos.portfolioproject.entity.entity;

import com.sintraqos.portfolioproject.statics.Enums;

import java.util.HashMap;

public class EntityMaster {
    private String entityName;
    private int entityLevel;
    HashMap<Enums.entitySkills, Integer> entitySkills;

    public String getEntityName() {
        return entityName;
    }

    public int getEntityLevel() {
        return entityLevel;
    }

    public HashMap<Enums.entitySkills, Integer> getEntitySkills() {
        return entitySkills;
    }

    public int getEntitySkill(Enums.entitySkills skill) {
        return entitySkills.get(skill);
    }

    @Override
    public String toString() {
        return entityName + " " + entityLevel;
    }
}
