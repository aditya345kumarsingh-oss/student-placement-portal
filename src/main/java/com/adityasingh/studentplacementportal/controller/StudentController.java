package com.adityasingh.studentplacementportal.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.adityasingh.studentplacementportal.entity.Placement;
import com.adityasingh.studentplacementportal.entity.Student;
import com.adityasingh.studentplacementportal.service.PlacementService;
import com.adityasingh.studentplacementportal.service.StudentService;

@Controller
public class StudentController {

    private final StudentService studentService;
    private final PlacementService placementService;

    public StudentController(
            StudentService studentService,
            PlacementService placementService) {

        this.studentService = studentService;
        this.placementService = placementService;
    }

    // Show student page
    @GetMapping("/students")
    public String studentPage(Model model) {

        model.addAttribute("student", new Student());

        model.addAttribute(
                "students",
                studentService.getAllStudents()
        );

        return "student";
    }

    // Save student
    @PostMapping("/students/save")
    public String saveStudent(
            @ModelAttribute Student student) {

        studentService.saveStudent(student);

        return "redirect:/students";
    }

    // Edit student
    @GetMapping("/students/edit/{id}")
    public String editStudent(
            @PathVariable Integer id,
            Model model) {

        Student student =
                studentService.getStudentById(id);

        model.addAttribute("student", student);

        model.addAttribute(
                "students",
                studentService.getAllStudents()
        );

        return "student";
    }

    // Search by ID or Name
    @GetMapping("/students/search")
    public String searchStudent(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String name,
            Model model) {

        model.addAttribute(
                "student",
                new Student()
        );

        // =========================
        // Search by Student ID
        // =========================
        if (id != null) {

            Student student =
                    studentService.searchStudentById(id);

            // Tell HTML that ID search happened
            model.addAttribute(
                    "idSearchPerformed",
                    true
            );

            model.addAttribute(
                    "searchResult",
                    student
            );

            if (student != null) {

                List<Placement> placements =
                        placementService
                                .getPlacementsByStudentId(id);

                model.addAttribute(
                        "placementResults",
                        placements
                );
            }
        }

        // =========================
        // Search by Student Name
        // =========================
        else if (name != null
                && !name.trim().isEmpty()) {

            model.addAttribute(
                    "searchResults",
                    studentService
                            .searchStudentsByName(name)
            );
        }

        model.addAttribute(
                "students",
                studentService.getAllStudents()
        );

        return "student";
    }

    // Delete student
    @GetMapping("/students/delete/{id}")
    public String deleteStudent(
            @PathVariable Integer id) {

        studentService.deleteStudent(id);

        return "redirect:/students";
    }
}