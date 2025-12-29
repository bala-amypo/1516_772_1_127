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

    // 🔹 Main constructor (Spring uses this)
    public ComplaintServiceImpl(
            ComplaintRepository complaintRepository,
            PriorityRuleService priorityRuleService,
            UserService userService) {
        this.complaintRepository = complaintRepository;
        this.priorityRuleService = priorityRuleService;
        this.userService = userService;
    }

    // 🔹 Test constructor (required by your TestNG tests)
    public ComplaintServiceImpl(
            ComplaintRepository complaintRepository,
            Object ignored1,
            Object ignored2,
            PriorityRuleService priorityRuleService) {
        this.complaintRepository = complaintRepository;
        this.priorityRuleService = priorityRuleService;
        this.userService = null;
    }

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
        complaint.setSubmittedOn(LocalDateTime.now());

        int score = priorityRuleService.computePriorityScore(complaint);
        complaint.setPriorityScore(score);

        return complaintRepository.save(complaint);
    }

    @Override
    public Complaint getComplaintById(Long id) {
        return complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
    }

    @Override
    public List<Complaint> getComplaintsForUser(User customer) {
        return complaintRepository.findByCustomer(customer);
    }

    @Override
    public List<Complaint> getPrioritizedComplaints() {
        return complaintRepository.findAllOrderByPriorityScoreDescCreatedAtAsc();
    }
}
