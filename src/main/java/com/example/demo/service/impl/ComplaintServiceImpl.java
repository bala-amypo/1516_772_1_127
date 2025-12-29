package com.example.demo.service.impl;

import com.example.demo.dto.ComplaintRequest;
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

    // 🔹 Constructor used by Spring Boot
    public ComplaintServiceImpl(
            ComplaintRepository complaintRepository,
            PriorityRuleService priorityRuleService,
            UserService userService) {
        this.complaintRepository = complaintRepository;
        this.priorityRuleService = priorityRuleService;
        this.userService = userService;
    }

    // 🔹 Constructor required ONLY for TestNG tests
    public ComplaintServiceImpl(
            ComplaintRepository complaintRepository,
            Object ignored1,
            Object ignored2,
            PriorityRuleService priorityRuleService) {
        this.complaintRepository = complaintRepository;
        this.priorityRuleService = priorityRuleService;
        this.userService = null;
    }

    // =========================================================
    // SUBMIT COMPLAINT
    // =========================================================
    @Override
    public Complaint submitComplaint(ComplaintRequest request, User customer) {

        Complaint complaint = new Complaint();
        complaint.setTitle(request.getTitle());
        complaint.setDescription(request.getDescription());
        complaint.setCategory(request.getCategory());
        complaint.setChannel(request.getChannel());
        complaint.setSeverity(request.getSeverity());
        complaint.setUrgency(request.getUrgency());
        complaint.setCustomer(customer);
        complaint.setStatus(Complaint.Status.NEW);
        complaint.setCreatedAt(LocalDateTime.now()); // ✅ FIXED

        int score = priorityRuleService.computePriorityScore(complaint);
        complaint.setPriorityScore(score);

        return complaintRepository.save(complaint);
    }

    // =========================================================
    // SAVE COMPLAINT (used by controller)
    // =========================================================
    @Override
    public Complaint saveComplaint(Complaint complaint) {
        return complaintRepository.save(complaint);
    }

    // =========================================================
    // GET COMPLAINT BY ID
    // =========================================================
    @Override
    public Complaint getComplaintById(Long id) {
        return complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
    }

    // =========================================================
    // GET COMPLAINTS FOR USER
    // =========================================================
    @Override
    public List<Complaint> getComplaintsForUser(User customer) {
        return complaintRepository.findByCustomer(customer);
    }

    // =========================================================
    // GET PRIORITIZED COMPLAINTS
    // =========================================================
    @Override
    public List<Complaint> getPrioritizedComplaints() {
        return complaintRepository.findAllOrderByPriorityScoreDescCreatedAtAsc();
    }
}
