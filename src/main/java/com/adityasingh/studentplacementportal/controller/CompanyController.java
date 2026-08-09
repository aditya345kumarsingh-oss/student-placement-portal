package com.adityasingh.studentplacementportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.adityasingh.studentplacementportal.entity.Company;
import com.adityasingh.studentplacementportal.service.CompanyService;

@Controller
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    // Show Company Registration Page
    @GetMapping("/companies")
    public String companyPage(Model model) {

        model.addAttribute("company", new Company());
        model.addAttribute("companies", companyService.getAllCompanies());

        return "company";
    }

    // Save Company
    @PostMapping("/companies/save")
    public String saveCompany(@ModelAttribute Company company) {

        companyService.saveCompany(company);

        return "redirect:/companies";
    }

    // Edit Company
    @GetMapping("/companies/edit/{id}")
    public String editCompany(@PathVariable Integer id, Model model) {

        Company company = companyService.getCompanyById(id);

        model.addAttribute("company", company);
        model.addAttribute("companies", companyService.getAllCompanies());

        return "company";
    }

    // Delete Company
    @GetMapping("/companies/delete/{id}")
    public String deleteCompany(@PathVariable Integer id) {

        companyService.deleteCompany(id);

        return "redirect:/companies";
    }
}