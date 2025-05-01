package com.sintraqos.portfolioproject.forum.forumPost.useCase;

// Project components
import com.sintraqos.portfolioproject.forum.forumPost.DAL.ForumPostRepository;
import com.sintraqos.portfolioproject.forum.forumPost.DTO.ForumPostDTO;
import com.sintraqos.portfolioproject.forum.forumPost.entities.ForumPostMessage;
import com.sintraqos.portfolioproject.shared.*;

// External components
import org.slf4j.Logger;

// Test components
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UseCaseAddForumPostTest {

    @Mock
    ForumPostRepository forumPostRepository;

    @Mock
    CensorService censorService;

    @Mock
    private SettingsHandler settingsHandler;

    @Mock
    private Logger logger;

    UseCaseAddForumPost useCaseAddForumPost;

    @BeforeEach
    void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
        useCaseAddForumPost = new UseCaseAddForumPost(forumPostRepository, censorService, settingsHandler, logger);
    }

    int minLength = 8;  // Min length of the message
    int maxLength = 16; // Max length of the message

    @Test
    void testAddForumPost_Fail_TooShort() {
        // Create ForumPost object
        String message = "Hi";
        ForumPostDTO forumPostDTO = new ForumPostDTO(1, 1, message);

        // Mock the correct method calls
        when(settingsHandler.getMessageMinLength()).thenReturn(minLength);
        when(settingsHandler.getMessageMaxLength()).thenReturn(maxLength);

        // Post the message using the base class
        ForumPostMessage result = useCaseAddForumPost.addForumPost(forumPostDTO);

        // Assert
        Assertions.assertFalse(result.isSuccessful());
        Assertions.assertEquals(Errors.FORUM_INVALID_LENGTH_SHORT.formatted(settingsHandler.getMessageMinLength(), settingsHandler.getMessageMaxLength()), result.getMessage());

        // Verify
        verify(logger).warn(anyString());
    }

    @Test
    void testAddForumPost_Fail_TooLong() {
        // Create ForumPost object
        String message = "This message is way too long to be posted";
        ForumPostDTO forumPostDTO = new ForumPostDTO(1, 1, message);

        // Mock the correct method calls
        when(settingsHandler.getMessageMinLength()).thenReturn(minLength);
        when(settingsHandler.getMessageMaxLength()).thenReturn(maxLength);

        // Post the message using the base class
        ForumPostMessage result = useCaseAddForumPost.addForumPost(forumPostDTO);

        // Assert
        Assertions.assertFalse(result.isSuccessful());
        Assertions.assertEquals(Errors.FORUM_INVALID_LENGTH_LONG.formatted(settingsHandler.getMessageMinLength(), settingsHandler.getMessageMaxLength()), result.getMessage());

        // Verify
        verify(logger).debug(anyString());
    }

    @Test
    void testAddForumPost_Success() {
        // Create ForumPost object
        String message = "Valid message";
        ForumPostDTO forumPostDTO = new ForumPostDTO(1, 1, message);

        // Mock the correct method calls
        when(settingsHandler.getMessageMinLength()).thenReturn(minLength);
        when(settingsHandler.getMessageMaxLength()).thenReturn(maxLength);
        when(censorService.validateString(message)).thenReturn(message);

        // Post the message using the base class
        ForumPostMessage result = useCaseAddForumPost.addForumPost(forumPostDTO);

        // Assert
        Assertions.assertTrue(result.isSuccessful());
        Assertions.assertEquals("Added new message: '%s'".formatted(message), result.getMessage());

        // Verify
        verify(forumPostRepository).save(any());
        verify(logger).debug(anyString());
    }
}
