package com.example.demo.controller;

import com.example.demo.dto.ComplaintRequest;
import com.example.demo.entity.Complaint;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ComplaintService;

import java.util.List;

public class ComplaintController {

    private final ComplaintService complaintService;
    private final UserRepository userRepository;

    public ComplaintController(
            ComplaintService complaintService,
            UserRepository userRepository
    ) {
        this.complaintService = complaintService;
        this.userRepository = userRepository;
    }

    public Complaint submitComplaint(Long userId, ComplaintRequest request) {
        User user = userRepository.findById(userId).orElse(null);
        return complaintService.submitComplaint(request, user);
    }

    public List<Complaint> getUserComplaints(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        return complaintService.getComplaintsForUser(user);
    }

    public List<Complaint> getAllPrioritized() {
        return complaintService.getPrioritizedComplaints();
    }

    public Complaint updateStatus(Long complaintId, Complaint.Status status) {
        Complaint complaint = complaintService.getComplaintById(complaintId);
        complaint.setStatus(status);
        return complaintService.saveComplaint(complaint);
    }
}
