package com.adityasingh.studentplacementportal.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    // Show Admin Login
    @GetMapping("/login")
    public String loginPage() {

        return "login";
    }

    // Process Admin Login
    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        if (username.equals("admin")
                && password.equals("admin123")) {

            session.setAttribute(
                    "loggedIn",
                    true
            );

            session.setAttribute(
                    "username",
                    username
            );

            return "redirect:/admin-dashboard";
        }

        model.addAttribute(
                "error",
                "Invalid username or password"
        );

        return "login";
    }

    // Admin Logout
    @GetMapping("/logout")
    public String logout(
            HttpSession session) {

        session.removeAttribute("loggedIn");
        session.removeAttribute("username");

        return "redirect:/";
    }
}