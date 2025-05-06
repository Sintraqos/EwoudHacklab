package com.sintraqos.portfolioproject.api.review;

// External components
import lombok.Getter;
import com.fasterxml.jackson.annotation.JsonProperty;

// Java components
import java.util.List;

@Getter
public class GameReviewObjects {
    @JsonProperty("baseGames")
    private List<GameReviewObject> reviewObjects;
}
