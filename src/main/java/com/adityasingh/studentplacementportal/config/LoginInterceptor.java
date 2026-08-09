package com.adityasingh.studentplacementportal.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

        // Get existing session
        // false means: don't create a new session
        HttpSession session = request.getSession(false);

        // Check whether admin is logged in
        boolean loggedIn =
                session != null
                && Boolean.TRUE.equals(
                        session.getAttribute("loggedIn")
                );

        // If admin is NOT logged in
        if (!loggedIn) {

            // Send user to Admin Login page
            response.sendRedirect("/login");

            return false;
        }

        // Admin is logged in, allow access
        return true;
    }
}