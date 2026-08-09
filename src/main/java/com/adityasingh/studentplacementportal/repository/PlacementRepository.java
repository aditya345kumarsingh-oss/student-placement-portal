package com.adityasingh.studentplacementportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adityasingh.studentplacementportal.entity.Placement;

@Repository
public interface PlacementRepository extends JpaRepository<Placement, Integer> {

    // Find placement using Student ID
    List<Placement> findByStudentStudentId(Integer studentId);

}