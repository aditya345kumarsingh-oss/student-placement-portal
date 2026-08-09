package com.adityasingh.studentplacementportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adityasingh.studentplacementportal.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    // Search students by name
    List<Student> findByNameContainingIgnoreCase(String name);

}