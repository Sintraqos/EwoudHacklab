package com.sintraqos.portfolioproject.core.dialogue;

import com.sintraqos.portfolioproject.statics.Enums;

import java.util.HashMap;

public class DialogueConditions {

    HashMap<Enums.entitySkills, Integer> skillCondition;

    // Check if the player met the needed conditions for the dialogue options to show up
    public boolean metDialogueConditions(){return true;}

    public DialogueConditions(){}

    public DialogueConditions(Enums.entitySkills skill, int value){
        skillCondition.put(skill, value);
    }
}
