package com.adityasingh.studentplacementportal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.adityasingh.studentplacementportal.entity.Placement;
import com.adityasingh.studentplacementportal.repository.PlacementRepository;

@Service
public class PlacementService {

    private final PlacementRepository placementRepository;

    public PlacementService(PlacementRepository placementRepository) {
        this.placementRepository = placementRepository;
    }

    // Get all placements
    public List<Placement> getAllPlacements() {
        return placementRepository.findAll();
    }

    // Get total number of placements
    public long getPlacementCount() {
        return placementRepository.count();
    }

    // Get placement by ID
    public Placement getPlacementById(Integer id) {
        return placementRepository.findById(id).orElse(null);
    }

    // Find placements using Student ID
    public List<Placement> getPlacementsByStudentId(Integer studentId) {
        return placementRepository.findByStudentStudentId(studentId);
    }

    // Save placement
    public void savePlacement(Placement placement) {
        placementRepository.save(placement);
    }

    // Delete placement
    public void deletePlacement(Integer id) {
        placementRepository.deleteById(id);
    }
}