package com.adityasingh.studentplacementportal.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.adityasingh.studentplacementportal.entity.Company;
import com.adityasingh.studentplacementportal.entity.Placement;
import com.adityasingh.studentplacementportal.service.CompanyService;
import com.adityasingh.studentplacementportal.service.PlacementService;
import com.adityasingh.studentplacementportal.service.StudentService;

@Controller
public class TpoDashboardController {

    private final StudentService studentService;
    private final CompanyService companyService;
    private final PlacementService placementService;


    public TpoDashboardController(
            StudentService studentService,
            CompanyService companyService,
            PlacementService placementService) {

        this.studentService = studentService;
        this.companyService = companyService;
        this.placementService = placementService;
    }


    @GetMapping("/tpo-dashboard")
    public String tpoDashboard(
            HttpSession session,
            Model model) {


        // Check TPO login
        Boolean tpoLoggedIn =
                (Boolean) session.getAttribute("tpoLoggedIn");


        if (tpoLoggedIn == null || !tpoLoggedIn) {

            return "redirect:/tpo-login";
        }


        // ===============================
        // Basic Counts
        // ===============================

        long totalStudents =
                studentService.getStudentCount();

        long totalCompanies =
                companyService.getCompanyCount();

        long totalPlacements =
                placementService.getPlacementCount();


        // Get all placement records
        List<Placement> placements =
                placementService.getAllPlacements();


        // ===============================
        // Placement Status Counts
        // ===============================

        long selectedStudents = 0;
        long pendingStudents = 0;
        long rejectedStudents = 0;


        for (Placement placement : placements) {

            String status = placement.getStatus();

            if (status != null) {

                if (status.equalsIgnoreCase("Selected")) {

                    selectedStudents++;

                }

                else if (status.equalsIgnoreCase("Pending")) {

                    pendingStudents++;

                }

                else if (status.equalsIgnoreCase("Rejected")) {

                    rejectedStudents++;

                }
            }
        }


        // ===============================
        // Highest Package
        // ===============================

        double highestPackage = 0.0;

        List<Company> companies =
                companyService.getAllCompanies();


        for (Company company : companies) {

            if (company.getPackageLpa() > highestPackage) {

                highestPackage =
                        company.getPackageLpa();
            }
        }


        // ===============================
        // Placement Percentage
        // ===============================

        double placementPercentage = 0.0;

        if (totalStudents > 0) {

            placementPercentage =
                    ((double) selectedStudents
                            / totalStudents) * 100;
        }


        // ===============================
        // Send Data To HTML
        // ===============================

        model.addAttribute(
                "totalStudents",
                totalStudents
        );

        model.addAttribute(
                "totalCompanies",
                totalCompanies
        );

        model.addAttribute(
                "totalPlacements",
                totalPlacements
        );

        model.addAttribute(
                "selectedStudents",
                selectedStudents
        );

        model.addAttribute(
                "pendingStudents",
                pendingStudents
        );

        model.addAttribute(
                "rejectedStudents",
                rejectedStudents
        );

        model.addAttribute(
                "highestPackage",
                highestPackage
        );

        model.addAttribute(
                "placementPercentage",
                placementPercentage
        );

        model.addAttribute(
                "username",
                session.getAttribute("tpoUsername")
        );


        return "tpo-dashboard";
    }
}