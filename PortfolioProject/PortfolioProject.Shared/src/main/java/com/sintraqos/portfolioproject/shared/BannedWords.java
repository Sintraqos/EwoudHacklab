package com.sintraqos.portfolioproject.shared;

// External components
import lombok.Getter;
import com.fasterxml.jackson.annotation.JsonProperty;

// Java components
import java.util.List;

@Getter
public class BannedWords {

    @JsonProperty("bannedWords")
    private List<String> bannedWords;
}