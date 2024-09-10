package com.sintraqos.portfolioproject.statics.dialogue;

import com.sintraqos.portfolioproject.statics.Enums;

import java.io.Serializable;
import java.util.*;

public class DialogueConditions  implements Serializable {

    EnumMap<Enums.entitySkills, Integer> skillCondition;

    // Check if the player met the needed conditions for the dialogue options to show up
    public boolean metDialogueConditions() {
        return true;
    }

    public DialogueConditions() {
        skillCondition = new EnumMap<>(Enums.entitySkills.class);
    }

    public DialogueConditions(Enums.entitySkills skill, int value) {
        skillCondition = new EnumMap<>(Enums.entitySkills.class);
        skillCondition.put(skill, value);
    }
}
