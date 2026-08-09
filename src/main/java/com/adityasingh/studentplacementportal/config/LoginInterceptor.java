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
        // false means: do not create a new session
        HttpSession session = request.getSession(false);

        // Check whether Teacher/Admin is logged in
        boolean loggedIn =
                session != null
                && Boolean.TRUE.equals(
                        session.getAttribute("loggedIn")
                );

        // If Teacher/Admin is NOT logged in
        if (!loggedIn) {

            // Redirect to Teacher/Admin login page
            response.sendRedirect("/teacher-login");

            return false;
        }

        // Teacher/Admin is logged in, allow access
        return true;
    }
}