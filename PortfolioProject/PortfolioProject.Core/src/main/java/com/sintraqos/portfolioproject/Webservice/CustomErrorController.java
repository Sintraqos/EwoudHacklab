package com.sintraqos.portfolioproject.Webservice;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
public class CustomErrorController implements ErrorController {

//    @RequestMapping("/error")
//    public String handleError(HttpServletRequest request, Model model) {
//        // Retrieve the error status code
//        Object status = request.getAttribute("javax.servlet.error.status_code");
//
//        if (status != null) {
//            int statusCode = Integer.valueOf(status.toString());
//
//            // You can add different messages based on the error status code
//            if (statusCode == 403) {
//                model.addAttribute("error", "You do not have permission to access this page.");
//            } else if (statusCode == 404) {
//                model.addAttribute("error", "Page not found.");
//            } else {
//                model.addAttribute("error", "An unexpected error occurred. Please try again.");
//            }
//        }
//
//        // You can also add custom attributes to provide more context about the error
//        return "error";
//    }
}