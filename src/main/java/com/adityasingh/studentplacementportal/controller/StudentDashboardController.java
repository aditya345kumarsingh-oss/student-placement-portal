package com.adityasingh.studentplacementportal.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.adityasingh.studentplacementportal.entity.Placement;
import com.adityasingh.studentplacementportal.entity.Student;
import com.adityasingh.studentplacementportal.service.PlacementService;
import com.adityasingh.studentplacementportal.service.StudentService;

@Controller
public class StudentDashboardController {

    private final StudentService studentService;
    private final PlacementService placementService;

    public StudentDashboardController(
            StudentService studentService,
            PlacementService placementService) {

        this.studentService = studentService;
        this.placementService = placementService;
    }

    @GetMapping("/student-dashboard")
    public String studentDashboard(
            HttpSession session,
            Model model) {

        // Check whether student is logged in
        Boolean studentLoggedIn =
                (Boolean) session.getAttribute("studentLoggedIn");

        if (studentLoggedIn == null || !studentLoggedIn) {
            return "redirect:/student-login";
        }

        // Get logged-in student ID from session
        Integer studentId =
                (Integer) session.getAttribute("studentId");

        // Get student details
        Student student =
                studentService.getStudentById(studentId);

        // Get placement details of this student
        List<Placement> placements =
                placementService.getPlacementsByStudentId(studentId);

        // Send data to HTML
        model.addAttribute("student", student);
        model.addAttribute("placements", placements);

        return "student-dashboard";
    }
}