package com.sintraqos.portfolioproject.testing;

import com.sintraqos.portfolioproject.forum.forumPost.entities.ForumPost;
import com.sintraqos.portfolioproject.forum.forumPost.service.ForumPostService;
import com.sintraqos.portfolioproject.testing.schedule.TestScheduleEventHandler;
import com.sintraqos.portfolioproject.testing.schedule.TestScheduler;
import com.sintraqos.portfolioproject.user.entities.User;
import com.sintraqos.portfolioproject.user.service.UserService;
import org.instancio.Instancio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class Testing {
    private final UserService userService;
    private final ForumPostService forumService;
    private final TestScheduler testScheduler;

    @Autowired
    public Testing(UserService userService, ForumPostService forumService, TestScheduler testScheduler) {
        this.userService = userService;
        this.forumService = forumService;
        this.testScheduler = testScheduler;
    }

    /**
     * After the initialization of Spring-Boot this will start
     */
    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
    }

    void createAccounts() {
        List<User> list = Instancio.ofList(User.class).size(10).create();
        for (User user : list) {
            userService.registerAccount(user.getUsername(), user.getUsername() + "@mail.com", user.getPassword());
        }
    }

    void postMessages() {
        Random rand = new Random();

        List<ForumPost> list = Instancio.ofList(ForumPost.class).size(rand.nextInt(1 , 1500)).create();
        for (ForumPost forumPost : list) {
            forumService.addForumPost(rand.nextInt(0 , 200) , rand.nextInt(0 , 200) , forumPost.getMessage());
        }
    }

    @EventListener
    public void handleScheduleTickEvent(TestScheduleEventHandler event) {
//        createAccounts();
        postMessages();
    }
}
