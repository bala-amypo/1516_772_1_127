package com.example.demo.controller;

import com.example.demo.entity.Complaint;
import com.example.demo.service.ComplaintStatusService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    private final ComplaintStatusService complaintStatusService;

    public StatusController(ComplaintStatusService complaintStatusService) {
        this.complaintStatusService = complaintStatusService;
    }

    // PUT /api/status/{complaintId}?status=RESOLVED
    @PutMapping("/{complaintId}")
    public Complaint updateStatus(@PathVariable Long complaintId,
                                  @RequestParam Complaint.Status status) {
        return complaintStatusService.updateStatus(complaintId, status);
    }

    // GET /api/status/{complaintId}
    @GetMapping("/{complaintId}")
    public Complaint getStatus(@PathVariable Long complaintId) {
        return complaintStatusService.getStatus(complaintId);
    }
}
