package com.sintraqos.portfolioproject.api.review;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class GameReviewObjects {
    @JsonProperty("baseGames")
    private List<GameReviewObject> reviewObjects;
}
