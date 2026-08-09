package com.adityasingh.studentplacementportal.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.adityasingh.studentplacementportal.entity.Student;
import com.adityasingh.studentplacementportal.service.StudentService;

@Controller
public class StudentLoginController {

    private final StudentService studentService;

    public StudentLoginController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Show student login page
    @GetMapping("/student-login")
    public String studentLoginPage() {

        return "student-login";
    }

    // Process student login
    @PostMapping("/student-login")
    public String studentLogin(
            @RequestParam Integer studentId,
            @RequestParam String email,
            HttpSession session,
            Model model) {

        Student student =
                studentService.getStudentById(studentId);

        // Check student exists and email matches
        if (student != null
                && student.getEmail().equalsIgnoreCase(email)) {

            // Store student information in session
            session.setAttribute(
                    "studentLoggedIn",
                    true
            );

            session.setAttribute(
                    "studentId",
                    student.getStudentId()
            );

            return "redirect:/student-dashboard";
        }

        model.addAttribute(
                "error",
                "Invalid Student ID or Email"
        );

        return "student-login";
    }

    // Student logout
    @GetMapping("/student-logout")
    public String studentLogout(HttpSession session) {

        session.removeAttribute("studentLoggedIn");
        session.removeAttribute("studentId");

        return "redirect:/student-login";
    }
}