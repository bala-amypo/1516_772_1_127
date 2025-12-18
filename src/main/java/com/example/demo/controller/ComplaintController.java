package com.example.demo.controller;

import com.example.demo.entity.Complaint;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ComplaintService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;
    private final UserRepository userRepository;

    public ComplaintController(ComplaintService complaintService,
                               UserRepository userRepository) {
        this.complaintService = complaintService;
        this.userRepository = userRepository;
    }

    // POST /complaints/submit
    @PostMapping("/submit")
    public Complaint submitComplaint(@RequestParam Long userId,
                                     @RequestBody Complaint complaint) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return complaintService.submitComplaint(complaint, user);
    }

    // GET /complaints/user/{userId}
    @GetMapping("/user/{userId}")
    public List<Complaint> getUserComplaints(@PathVariable Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return complaintService.getComplaintsForUser(user);
    }

    // GET /complaints/prioritized
    @GetMapping("/prioritized")
    public List<Complaint> getPrioritizedComplaints() {
        return complaintService.getPrioritizedComplaints();
    }

    // PUT /complaints/status/{id}
    @PutMapping("/status/{id}")
    public Complaint updateStatus(@PathVariable Long id,
                                  @RequestParam Complaint.Status status) {

        Complaint complaint = complaintService.getComplaintById(id);
        complaint.setStatus(status);
        return complaintService.saveComplaint(complaint);
    }
}
