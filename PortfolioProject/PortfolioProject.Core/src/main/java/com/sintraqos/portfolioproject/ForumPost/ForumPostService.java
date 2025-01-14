package com.sintraqos.portfolioproject.ForumPost;

import com.sintraqos.portfolioproject.Messages.Message;
import com.sintraqos.portfolioproject.Game.GameRepository;
import com.sintraqos.portfolioproject.User.UserRepository;
import com.sintraqos.portfolioproject.Statics.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ForumPostService {

    private final ForumPostRepository forumPostRepository;
    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    @Autowired
    public ForumPostService(
            ForumPostRepository forumPostRepository,
            GameRepository gameRepository,
            UserRepository userRepository
    ) {
        this.forumPostRepository = forumPostRepository;
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    /**
     * Add a new game to the database
     *
     * @param forumPostDTO the forumPost to be added
     */
    public Message addForumPost(ForumPostDTO forumPostDTO) {
        // Check if a user with the given ID exists
        if (userRepository.findByAccountID(forumPostDTO.getAccountID()) == null) {
            return new Message(Errors.FIND_ACCOUNT_ID_FAILED.formatted(forumPostDTO.getAccountID()));
        }
        // Check if a game with the given ID exists
        if (gameRepository.findByGameID(forumPostDTO.getGameID()) == null) {
            return new Message(Errors.FIND_GAME_ID_FAILED.formatted(forumPostDTO.getGameID()));
        }

        // Create a new ForumPostEntity object and save that in the database
        ForumPostEntity forumPostEntity = new ForumPostEntity(forumPostDTO.getAccountID(), forumPostDTO.getGameID(), forumPostDTO.getMessage());
        forumPostRepository.save(forumPostEntity);

        return new Message(true, "Added new message: '%s'".formatted(forumPostDTO.getMessage()));
    }

    /**
     * Find all forumPosts using a GameID
     *
     * @param gameID the ID of the game
     */
    public ForumPostMessage getForumPosts_Game(int gameID, PageRequest pageRequest) {
        Page<ForumPostEntity> forumPostEntities = forumPostRepository.findAllByGameIDOrderByPostDateDesc(gameID, pageRequest);

        // Check if the list returned is null or empty
        if (forumPostEntities == null) {
            return new ForumPostMessage(Errors.FORUM_GAME_ID_FAILED.formatted(gameID));
        }

        return new ForumPostMessage(forumPostEntities, "Forum posts found");
    }

    /**
     * Find all forumPosts using their UserID
     *
     * @param accountID the ID of the account
     */
    public ForumPostMessage getForumPosts_Account(int accountID, PageRequest pageRequest) {
        Page<ForumPostEntity> forumPostEntities = forumPostRepository.findAllByAccountIDOrderByPostDateDesc(accountID, pageRequest);

        // Check if the list returned is null or empty
        if (forumPostEntities == null) {
            return new ForumPostMessage(Errors.FORUM_ACCOUNT_ID_FAILED.formatted(accountID));
        }

        return new ForumPostMessage(forumPostEntities, "Forum posts found");
    }
}
