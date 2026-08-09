package com.adityasingh.studentplacementportal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.adityasingh.studentplacementportal.entity.Student;
import com.adityasingh.studentplacementportal.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get total student count
    public long getStudentCount() {
        return studentRepository.count();
    }

    // Save student
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    // Get student by ID
    public Student getStudentById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    // Search student by ID
    public Student searchStudentById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    // Search student by name
    public List<Student> searchStudentsByName(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name);
    }

    // Delete student
    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }
}