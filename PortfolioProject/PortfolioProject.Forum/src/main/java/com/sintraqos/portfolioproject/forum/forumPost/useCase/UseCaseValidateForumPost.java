package com.sintraqos.portfolioproject.forum.forumPost.useCase;

// Project components
import com.sintraqos.portfolioproject.forum.forumPost.DAL.ForumPostEntity;
import com.sintraqos.portfolioproject.forum.forumPost.DAL.ForumPostRepository;
import com.sintraqos.portfolioproject.forum.forumPost.DTO.ForumPostDTO;
import com.sintraqos.portfolioproject.forum.forumPost.entities.ForumPostMessage;
import com.sintraqos.portfolioproject.shared.CensorService;
import com.sintraqos.portfolioproject.shared.Errors;
import com.sintraqos.portfolioproject.shared.SettingsHandler;

// Spring components
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

// External components
import org.slf4j.Logger;
import lombok.Getter;

// Java components
import java.time.*;
import java.util.List;
import java.util.regex.Pattern;

/**
 * UseCase for handling validating a forumPost
 */
@Getter
@Component
public class UseCaseValidateForumPost {
    private final ForumPostRepository forumPostRepository;
    private final CensorService censorService;
    private final SettingsHandler settingsHandler;
    private final Logger logger;

    int maxRepetitions = 4;

    private final Pattern SQL_PATTERN = Pattern.compile("(?i)(SELECT|INSERT|UPDATE|DELETE|DROP|ALTER|CREATE|EXEC|UNION|--|;|\\*|FROM|WHERE)[^\\w]*\\b(SELECT|INSERT|UPDATE|DELETE|DROP|ALTER|CREATE|EXEC|UNION|WHERE|FROM|\\*|;|--|\\b.*\\b)\\b");
    private final Pattern URL_PATTERN = Pattern.compile("(?i)\\b(?:https?|ftp)://[^\\s/$.?#].\\S*|(?:www\\.)?[a-z0-9-]+(?:\\.[a-z0-9-]+)+(?::\\d+)?(?:/\\S*)?\\b");
    private final Pattern CODE_PATTERN = Pattern.compile(".*<[^>]+>.*");

    @Autowired
    public UseCaseValidateForumPost(ForumPostRepository forumPostRepository,
                                    CensorService censorService,
                                    SettingsHandler settingsHandler,
                                    Logger logger) {
        this.forumPostRepository = forumPostRepository;
        this.censorService = censorService;
        this.settingsHandler = settingsHandler;
        this.logger = logger;
    }

    /**
     * Check if the user can post a new message, prevent spamming a lot of messages in a short time frame
     *
     * @param accountID the ID of the account
     */
    boolean canPost(int accountID) {
        // Check for post cooldown
        List<ForumPostEntity> recentPosts = forumPostRepository.findAllByAccountIDOrderByPostDateDesc(accountID, PageRequest.of(0, 1));

        // Check if the account has posted a previous message
        if (!recentPosts.isEmpty()) {
            // Get the newest message
            ForumPostEntity lastPost = recentPosts.get(0);
            // Calculate the time between the previous message and now
            float secondsSinceLastPost = Duration.between(lastPost.getPostDate().toLocalDateTime(), LocalDateTime.now()).getSeconds();

            // Return the result
            return secondsSinceLastPost >= settingsHandler.getMessageCooldown();
        }

        // Since there were no previous messages, the user can always post a message
        return true;
    }

    /**
     * Check if the message contains repeating characters, prevents sending a message like: 'aaaa', but still be able to sent a message containing 'aa'
     *
     * @param message the message to check
     */
    boolean hasExcessiveRepeatedChars(String message) {
        if (message == null || message.isEmpty()) return false;

        char prevChar = message.charAt(0);
        int count = 1;

        for (int i = 1; i < message.length(); i++) {
            char currentChar = message.charAt(i);
            if (currentChar == prevChar) {
                count++;
                if (count > maxRepetitions) {
                    return true;
                }
            } else {
                count = 1;
                prevChar = currentChar;
            }
        }

        return false;
    }

    /**
     * Check if the message contains an SQL query
     *
     * @param message the message to check
     */
    boolean containsSQL(String message) {
        return SQL_PATTERN.matcher(message).find();
    }

    /**
     * Check if the message contains a URl
     *
     * @param message the message to check
     */
    boolean containsURL(String message) {
        return URL_PATTERN.matcher(message).find();
    }

    /**
     * Check if the message contains HTML or code fragments
     *
     * @param message the message to check
     */
    boolean containsHTMLOrScript(String message) {
        return CODE_PATTERN.matcher(message).find();
    }

    /**
     * Check if the message contains characters not supported by the database
     *
     * @param message the message to check
     */
    boolean containsUnsupportedLanguage(String message) {
        return message.matches("[^\\x00-\\x7F]+");  // Matches non-ASCII characters
    }

    /**
     * Check if the given message is valid to be posted
     *
     * @param forumPost the forum post to validate
     * @return either a new ForumPostMessage containing the error, or the validated message that will be posted
     */
    public ForumPostMessage validateForumPost(ForumPostDTO forumPost) {
        // Check if the message is a valid length
        int messageLength = forumPost.getMessage().length();

        String baseMessage = "Failed to add message from user with ID: '%s'. Reason: %s";
        String validatedMessage = censorService.validateString(forumPost.getMessage()); // Immediately clean up the given message from banned words

        // Spam protection
        if (!canPost(forumPost.getAccountID())) {
            String message = Errors.FORUM_POST_TOO_SOON;
            logger.warn(message);

            return new ForumPostMessage(message);
        }

        // Check if the message contains excessive use of repeated characters
        if (hasExcessiveRepeatedChars(validatedMessage)) {
            String message = Errors.FORUM_HAS_EXCESSIVE_REPEATED_CHARS;
            logger.warn(baseMessage.formatted(forumPost.getAccountID(), message));

            return new ForumPostMessage(message);
        }

        // Check if the message contains a SQL query
        if (containsSQL(validatedMessage)) {
            String message = Errors.FORUM_POST_CONTAINS_SQL;
            logger.warn(baseMessage.formatted(forumPost.getAccountID(), message));

            return new ForumPostMessage(message);
        }

        // Check if the message contains a URL
        if (containsURL(validatedMessage)) {
            String message = Errors.FORUM_POST_CONTAINS_URL;
            logger.warn(baseMessage.formatted(forumPost.getAccountID(), message));

            return new ForumPostMessage(message);
        }

        // Check if the message contains HTML or other code fragments
        if (containsHTMLOrScript(validatedMessage)) {
            String message = Errors.FORUM_POST_CONTAINS_HTML_CODE;
            logger.warn(baseMessage.formatted(forumPost.getAccountID(), message));

            return new ForumPostMessage(message);
        }

        // Check if the message contains non-supported characters
        if (containsUnsupportedLanguage(validatedMessage)) {
            String message = Errors.FORUM_POST_CONTAINS_NON_SUPPORTED_CHARS;
            logger.warn(baseMessage.formatted(forumPost.getAccountID(), message));

            return new ForumPostMessage(message);
        }

        // Message too short
        if (messageLength < settingsHandler.getMessageMinLength()) {
            String message = Errors.FORUM_INVALID_LENGTH_SHORT.formatted(settingsHandler.getMessageMinLength(), settingsHandler.getMessageMaxLength());
            logger.warn(baseMessage.formatted(forumPost.getAccountID(), message));

            return new ForumPostMessage(message);
        }

        // Message too long
        if (messageLength > settingsHandler.getMessageMaxLength()) {
            String message = Errors.FORUM_INVALID_LENGTH_LONG.formatted(settingsHandler.getMessageMinLength(), settingsHandler.getMessageMaxLength());
            logger.warn(baseMessage.formatted(forumPost.getAccountID(), message));

            return new ForumPostMessage(message);
        }

        return new ForumPostMessage(true, validatedMessage);
    }
}
