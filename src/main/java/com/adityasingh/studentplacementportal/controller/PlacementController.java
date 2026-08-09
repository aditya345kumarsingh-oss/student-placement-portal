package com.adityasingh.studentplacementportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.adityasingh.studentplacementportal.entity.Placement;
import com.adityasingh.studentplacementportal.service.CompanyService;
import com.adityasingh.studentplacementportal.service.PlacementService;
import com.adityasingh.studentplacementportal.service.StudentService;

@Controller
public class PlacementController {

    private final PlacementService placementService;
    private final StudentService studentService;
    private final CompanyService companyService;

    public PlacementController(
            PlacementService placementService,
            StudentService studentService,
            CompanyService companyService) {

        this.placementService = placementService;
        this.studentService = studentService;
        this.companyService = companyService;
    }

    // Show Placement Page
    @GetMapping("/placements")
    public String placementPage(Model model) {

        model.addAttribute("placement", new Placement());

        model.addAttribute(
                "placements",
                placementService.getAllPlacements()
        );

        model.addAttribute(
                "students",
                studentService.getAllStudents()
        );

        model.addAttribute(
                "companies",
                companyService.getAllCompanies()
        );

        return "placement";
    }

    // Save Placement
    @PostMapping("/placements/save")
    public String savePlacement(
            @ModelAttribute Placement placement) {

        placementService.savePlacement(placement);

        return "redirect:/placements";
    }

    // Edit Placement
    @GetMapping("/placements/edit/{id}")
    public String editPlacement(
            @PathVariable Integer id,
            Model model) {

        Placement placement =
                placementService.getPlacementById(id);

        model.addAttribute("placement", placement);

        model.addAttribute(
                "placements",
                placementService.getAllPlacements()
        );

        model.addAttribute(
                "students",
                studentService.getAllStudents()
        );

        model.addAttribute(
                "companies",
                companyService.getAllCompanies()
        );

        return "placement";
    }

    // Delete Placement
    @GetMapping("/placements/delete/{id}")
    public String deletePlacement(
            @PathVariable Integer id) {

        placementService.deletePlacement(id);

        return "redirect:/placements";
    }
}