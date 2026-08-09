package com.adityasingh.studentplacementportal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.adityasingh.studentplacementportal.entity.Company;
import com.adityasingh.studentplacementportal.repository.CompanyRepository;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    // Get all companies
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    // Get total number of companies
    public long getCompanyCount() {
        return companyRepository.count();
    }

    // Save company
    public void saveCompany(Company company) {
        companyRepository.save(company);
    }

    // Get company by ID
    public Company getCompanyById(Integer id) {
        return companyRepository.findById(id).orElse(null);
    }

    // Delete company
    public void deleteCompany(Integer id) {
        companyRepository.deleteById(id);
    }
}