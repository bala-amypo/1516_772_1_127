package com.example.demo.service.impl;

import com.example.demo.entity.Complaint;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ComplaintRepository;
import com.example.demo.service.ComplaintService;
import com.example.demo.service.PriorityRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository repo;
    private final PriorityRuleService ruleService;

    public ComplaintServiceImpl(ComplaintRepository repo,
                                PriorityRuleService ruleService) {
        this.repo = repo;
        this.ruleService = ruleService;
    }

    @Override
    public Complaint submitComplaint(Complaint complaint, User customer) {
        complaint.setCustomer(customer);
        complaint.setPriorityScore(
                ruleService.computePriorityScore(complaint)
        );
        return repo.save(complaint);
    }

    @Override
    public List<Complaint> getComplaintsForUser(User customer) {
        return repo.findByCustomer(customer);
    }

    @Override
    public List<Complaint> getPrioritizedComplaints() {
        return repo.findAllOrderByPriorityScoreDescCreatedAtAsc();
    }

    @Override
    public Complaint getComplaintById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint not found"));
    }

    @Override
    public Complaint saveComplaint(Complaint complaint) {
        return repo.save(complaint);
    }
}
