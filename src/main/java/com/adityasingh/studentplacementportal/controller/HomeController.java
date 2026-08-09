package com.adityasingh.studentplacementportal.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.adityasingh.studentplacementportal.service.CompanyService;
import com.adityasingh.studentplacementportal.service.PlacementService;
import com.adityasingh.studentplacementportal.service.StudentService;

@Controller
public class HomeController {

    private final StudentService studentService;
    private final CompanyService companyService;
    private final PlacementService placementService;

    public HomeController(
            StudentService studentService,
            CompanyService companyService,
            PlacementService placementService) {

        this.studentService = studentService;
        this.companyService = companyService;
        this.placementService = placementService;
    }

    // Public home page
    @GetMapping("/")
    public String home() {

        return "index";
    }

    // Admin Dashboard
    @GetMapping("/admin-dashboard")
    public String adminDashboard(
            Model model,
            HttpSession session) {

        Boolean loggedIn =
                (Boolean) session.getAttribute("loggedIn");

        if (loggedIn == null || !loggedIn) {

            return "redirect:/login";
        }

        model.addAttribute(
                "studentCount",
                studentService.getStudentCount()
        );

        model.addAttribute(
                "companyCount",
                companyService.getCompanyCount()
        );

        model.addAttribute(
                "placementCount",
                placementService.getPlacementCount()
        );

        model.addAttribute(
                "username",
                session.getAttribute("username")
        );

        return "admin-dashboard";
    }
}