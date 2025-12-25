package com.example.demo.controller;

import com.example.demo.entity.Complaint;
import com.example.demo.service.ComplaintService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/status")
public class StatusController {

    private final ComplaintService complaintService;

    public StatusController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @GetMapping("/{complaintId}")
    public ResponseEntity<Complaint.Status> getStatus(@PathVariable Long complaintId) {

        Complaint complaint = complaintService.getComplaintById(complaintId);
        if (complaint == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(complaint.getStatus());
    }
}
