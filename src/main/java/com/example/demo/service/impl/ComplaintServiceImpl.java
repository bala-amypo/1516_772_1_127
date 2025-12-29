package com.example.demo.service.impl;

import com.example.demo.entity.Complaint;
import com.example.demo.entity.User;
import com.example.demo.repository.ComplaintRepository;
import com.example.demo.service.ComplaintService;
import com.example.demo.service.PriorityRuleService;
import com.example.demo.service.UserService;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final PriorityRuleService priorityRuleService;
    private final UserService userService;

    // ✅ REQUIRED constructor (TEST + SPRING both use this)
    public ComplaintServiceImpl(
            ComplaintRepository complaintRepository,
            PriorityRuleService priorityRuleService,
            UserService userService
    ) {
        this.complaintRepository = complaintRepository;
        this.priorityRuleService = priorityRuleService;
        this.userService = userService;
    }

    // ✅ Used by controller
    @Override
    public Complaint submitComplaint(Complaint complaint, User user) {

        complaint.setUser(user);

        // ⚠️ Use ONLY fields that exist in entity
        complaint.setCreatedOn(LocalDateTime.now());

        int priorityScore =
                priorityRuleService.computePriorityScore(complaint);

        complaint.setPriorityScore(priorityScore);

        return complaintRepository.save(complaint);
    }

    // ✅ Fixes abstract method error
    @Override
    public Complaint getComplaintById(Long id) {
        return complaintRepository.findById(id).orElse(null);
    }

    @Override
    public List<Complaint> getComplaintsByUser(User user) {
        return complaintRepository.findByUser(user);
    }

    @Override
    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    // ✅ Fixes controller error
    @Override
    public Complaint saveComplaint(Complaint complaint) {
        return complaintRepository.save(complaint);
    }
}
