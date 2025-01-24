package com.sintraqos.portfolioproject.statics;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class BannedWords {

    @JsonProperty("bannedWords")
    private List<String> bannedWords;

    // Getters and setters
    public List<String> getBannedWords() {
        return bannedWords;
    }

    public void setBannedWords(List<String> bannedWords) {
        this.bannedWords = bannedWords;
    }
}