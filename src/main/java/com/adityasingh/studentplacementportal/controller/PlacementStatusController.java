package com.adityasingh.studentplacementportal.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.adityasingh.studentplacementportal.entity.Placement;
import com.adityasingh.studentplacementportal.entity.Student;
import com.adityasingh.studentplacementportal.service.PlacementService;
import com.adityasingh.studentplacementportal.service.StudentService;

@Controller
public class PlacementStatusController {

    private final StudentService studentService;
    private final PlacementService placementService;

    public PlacementStatusController(
            StudentService studentService,
            PlacementService placementService) {

        this.studentService = studentService;
        this.placementService = placementService;
    }


    @GetMapping("/placement-status")
    public String placementStatus(
            @RequestParam(required = false) String status,
            Model model) {


        // Get all students
        List<Student> allStudents =
                studentService.getAllStudents();


        // Store final information for every student
        Map<Integer, String> statusMap =
                new HashMap<>();

        Map<Integer, String> companyMap =
                new HashMap<>();

        Map<Integer, Double> packageMap =
                new HashMap<>();


        // Students that will finally be displayed
        List<Student> displayStudents =
                new ArrayList<>();


        for (Student student : allStudents) {

            List<Placement> placements =
                    placementService
                            .getPlacementsByStudentId(
                                    student.getStudentId()
                            );


            String finalStatus = "Not Placed";
            String companyName = "-";
            double packageLpa = 0.0;

            Placement chosenPlacement = null;


           
            // First priority: Selected
           

            for (Placement placement : placements) {

                if (placement.getStatus() != null
                        && placement.getStatus()
                        .equalsIgnoreCase("Selected")) {

                    chosenPlacement = placement;
                    finalStatus = "Selected";

                    break;
                }
            }


            
            // Second priority: Pending
            

            if (chosenPlacement == null) {

                for (Placement placement : placements) {

                    if (placement.getStatus() != null
                            && placement.getStatus()
                            .equalsIgnoreCase("Pending")) {

                        chosenPlacement = placement;
                        finalStatus = "Pending";

                        break;
                    }
                }
            }


            
            // Third priority: Rejected
          

            if (chosenPlacement == null) {

                for (Placement placement : placements) {

                    if (placement.getStatus() != null
                            && placement.getStatus()
                            .equalsIgnoreCase("Rejected")) {

                        chosenPlacement = placement;
                        finalStatus = "Rejected";

                        break;
                    }
                }
            }


            
            // Company Information
           

            if (chosenPlacement != null
                    && chosenPlacement.getCompany() != null) {

                companyName =
                        chosenPlacement
                                .getCompany()
                                .getCompanyName();

                packageLpa =
                        chosenPlacement
                                .getCompany()
                                .getPackageLpa();
            }


            statusMap.put(
                    student.getStudentId(),
                    finalStatus
            );

            companyMap.put(
                    student.getStudentId(),
                    companyName
            );

            packageMap.put(
                    student.getStudentId(),
                    packageLpa
            );


            
            // Filter
            

            if (status == null
                    || status.isBlank()
                    || status.equalsIgnoreCase("All")) {

                displayStudents.add(student);
            }

            else if (finalStatus
                    .equalsIgnoreCase(status)) {

                displayStudents.add(student);
            }
        }


        // Send everything to HTML

        model.addAttribute(
                "students",
                displayStudents
        );

        model.addAttribute(
                "statusMap",
                statusMap
        );

        model.addAttribute(
                "companyMap",
                companyMap
        );

        model.addAttribute(
                "packageMap",
                packageMap
        );

        model.addAttribute(
                "selectedFilter",
                status == null ? "All" : status
        );


        return "placement-status";
    }
}