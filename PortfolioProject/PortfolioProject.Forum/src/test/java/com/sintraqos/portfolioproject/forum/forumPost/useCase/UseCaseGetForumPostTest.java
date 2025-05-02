package com.sintraqos.portfolioproject.forum.forumPost.useCase;

// Project components
import com.sintraqos.portfolioproject.forum.forumPost.DAL.*;
import com.sintraqos.portfolioproject.forum.forumPost.entities.ForumPostMessage;
import com.sintraqos.portfolioproject.shared.Errors;

// Spring components
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;

// External components
import org.slf4j.Logger;
import lombok.Getter;

// Java components
import java.util.List;

// Test components
import org.junit.jupiter.api.*;
import org.mockito.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@Getter
@Component
class UseCaseGetForumPostTest {

    @Mock
    ForumPostRepository forumPostRepository;

    @Mock
    private Logger logger;

    UseCaseGetForumPost useCaseGetForumPost;

    @BeforeEach
    void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
        useCaseGetForumPost = new UseCaseGetForumPost(forumPostRepository, logger);
    }

    int gameID = 0;     // ID of the game which forum posts we wish to retrieve
    int accountID = 0;  // ID of the account which forum posts we wish to retrieve
    PageRequest pageRequest = PageRequest.of(0, 10);

    @Test
    void testGetForumPostsGame_Fail_GameDoesNotExist() {
        // Mock the correct method calls
        when(forumPostRepository.findAllByGameIDOrderByPostDateDesc(gameID, pageRequest)).thenReturn(null);

        // Retrieve the message using the base class
        ForumPostMessage result = useCaseGetForumPost.getForumPosts_Game(gameID, pageRequest);

        // Assert
        Assertions.assertFalse(result.isSuccessful());
        Assertions.assertEquals(Errors.FORUM_GAME_ID_FAILED.formatted(gameID), result.getMessage());

        // Verify results;
        verify(logger, times(2)).debug(anyString());
    }

    @Test
    void testGetForumPostsGame_Success() {
        // Generate a mock Page<ForumPostEntity>
        Page<ForumPostEntity> mockedPage = new PageImpl<>(
                List.of(new ForumPostEntity(), new ForumPostEntity()),
                Pageable.ofSize(10),
                10
        );

        // Mock the correct method calls
        when(forumPostRepository.findAllByGameIDOrderByPostDateDesc(gameID, pageRequest)).thenReturn(mockedPage);

        // Retrieve the message using the base class
        ForumPostMessage result = useCaseGetForumPost.getForumPosts_Game(gameID, pageRequest);

        // Assert
        Assertions.assertTrue(result.isSuccessful());
        Assertions.assertEquals("Game's forum posts found", result.getMessage());

        // Verify
        verify(forumPostRepository).findAllByGameIDOrderByPostDateDesc(gameID, pageRequest);
        verify(logger, times(2)).debug(anyString());
    }

    @Test
    void testGetForumPostsAccount_Fail_UserDoesNotExist() {
        // Mock the correct method calls
        when(forumPostRepository.findAllByAccountIDOrderByPostDateDesc(accountID, pageRequest)).thenReturn(null);

        // Retrieve the message using the base class
        ForumPostMessage result = useCaseGetForumPost.getForumPosts_Account(accountID, pageRequest);

        // Assert
        Assertions.assertFalse(result.isSuccessful());
        Assertions.assertEquals(Errors.FORUM_USER_ID_FAILED.formatted(accountID), result.getMessage());

        // Verify results
        verify(logger, times(2)).debug(anyString());
    }

    @Test
    void testGetForumPostsAccount_Success() {
        // Generate a mock Page<ForumPostEntity>
        Page<ForumPostEntity> mockedPage = new PageImpl<>(
                List.of(new ForumPostEntity(), new ForumPostEntity()),
                Pageable.ofSize(10),
                10
        );

        // Mock the correct method calls
        when(forumPostRepository.findAllByAccountIDOrderByPostDateDesc(accountID, pageRequest)).thenReturn((List<ForumPostEntity>) mockedPage);

        // Retrieve the message using the base class
        ForumPostMessage result = useCaseGetForumPost.getForumPosts_Account(accountID, pageRequest);

        // Assert
        Assertions.assertTrue(result.isSuccessful());
        Assertions.assertEquals("Account's forum posts found", result.getMessage());

        // Verify results;
        verify(forumPostRepository).findAllByAccountIDOrderByPostDateDesc(accountID, pageRequest);
        verify(logger, times(2)).debug(anyString());
    }
}
