package com.sintraqos.portfolioproject.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class BannedWords {

    @JsonProperty("bannedWords")
    private List<String> bannedWords;
}