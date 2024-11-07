package com.sintraqos.portfolioproject.Webservice;

import com.sintraqos.portfolioproject.Account.AccountManager;
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

    private final AccountManager accountManager;

    public CustomLogoutSuccessHandler(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        // Get the currently authenticated username
        String username = (String) request.getSession().getAttribute("username");

        if (username != null) {
            Message message = accountManager.logoutAccount(username);
            if (!message.isSuccessful()) {
                // Add an error message to the redirect attributes
                request.getSession().setAttribute("error", message.getMessage());
            }
        }

        // Clear out the session
        request.getSession().invalidate();

        // Redirect to the desired page after logout
        response.sendRedirect("/home");
    }
}
