package com.sintraqos.portfolioproject.Webservice;

import com.sintraqos.portfolioproject.User.UserManager;
import com.sintraqos.portfolioproject.Messages.Message;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private final UserManager userManager;

    public CustomLogoutSuccessHandler(UserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        // Clear out the session
        request.getSession().invalidate();

        // Redirect to the desired page after logout
        response.sendRedirect("/home");
    }
}
