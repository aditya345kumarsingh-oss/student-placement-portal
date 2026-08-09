package com.adityasingh.studentplacementportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adityasingh.studentplacementportal.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

}