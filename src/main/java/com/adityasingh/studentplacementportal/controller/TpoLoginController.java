package com.adityasingh.studentplacementportal.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TpoLoginController {

    // Show TPO Login Page
    @GetMapping("/tpo-login")
    public String tpoLoginPage() {

        return "tpo-login";
    }


    // Process TPO Login
    @PostMapping("/tpo-login")
    public String tpoLogin(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        // Temporary demo credentials
        if (username.equals("tpo")
                && password.equals("tpo123")) {

            session.setAttribute(
                    "tpoLoggedIn",
                    true
            );

            session.setAttribute(
                    "tpoUsername",
                    username
            );

            return "redirect:/tpo-dashboard";
        }


        model.addAttribute(
                "error",
                "Invalid username or password"
        );

        return "tpo-login";
    }


    // TPO Logout
    @GetMapping("/tpo-logout")
    public String tpoLogout(
            HttpSession session) {

        session.removeAttribute("tpoLoggedIn");
        session.removeAttribute("tpoUsername");

        return "redirect:/tpo-login";
    }
}