package com.example.demo.controller;

import com.example.demo.entity.Complaint;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ComplaintRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    private final ComplaintRepository complaintRepository;

    public StatusController(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    // GET /status/history/{complaintId}
    @GetMapping("/history/{complaintId}")
    public Complaint getStatusHistory(@PathVariable Long complaintId) {
        return complaintRepository.findById(complaintId)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint not found"));
    }
}
