package com.sintraqos.portfolioproject.API.Review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
@AllArgsConstructor
public class GameReviewObject {
    private String gameName;
    private String gameDescription;
    private String gameDeveloper;
    private String gamePublisher;
    @Range(min = 0, max = 10)   // Make the  gameScore only be able to be between 0 and 10
    private int gameScore;
    private String gameReview;

    @Override
    public String toString(){
        return "%s: %s".formatted(gameName, new String("%s/10".formatted(gameScore)));
    }
}
