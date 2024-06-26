package com.sintraqos.portfolioproject.entity.entity;

public class EntityMaster {
    private String entityName;
    private int entityLevel;

    public String getEntityName() {
        return entityName;
    }

    public int getEntityLevel() {
        return entityLevel;
    }

    @Override
    public String toString() {
        return entityName + " " + entityLevel;
    }
}
