package com.sintraqos.portfolioproject.forum.forumPost.entities;

// External components
import lombok.*;

// Java components
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Getter
@AllArgsConstructor
public class ForumPost {

    private final int forumPostID;
    private final int accountID;
    private final String username;
    private final int gameID;
    private final String message;
    private Timestamp postDate;

    public String getTimestamp() {
        // Get current date and time as: 01-02-1234 - 12:34
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy - HH:mm");
        return sdf.format(postDate);
    }

    @Override
    public String toString() {
        // Return the string as: '[DD-MM-YYYY - HH:MM] Username: Message'
        return "[%s] %s: %s".formatted(getTimestamp(), username, message);
    }

}
