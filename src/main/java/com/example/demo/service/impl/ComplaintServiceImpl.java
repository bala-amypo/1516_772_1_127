package com.example.demo.service.impl;

import com.example.demo.dto.ComplaintRequest;
import com.example.demo.entity.*;
import com.example.demo.repository.ComplaintRepository;
import com.example.demo.service.*;

import java.util.List;

public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository repo;
    private final PriorityRuleService ruleService;

    public ComplaintServiceImpl(
            ComplaintRepository repo,
            Object a, Object b,
            PriorityRuleService ruleService) {
        this.repo = repo;
        this.ruleService = ruleService;
    }

    @Override
    public Complaint submitComplaint(ComplaintRequest req, User customer) {
        Complaint c = new Complaint();
        c.setTitle(req.getTitle());
        c.setDescription(req.getDescription());
        c.setCategory(req.getCategory());
        c.setChannel(req.getChannel());
        c.setSeverity(req.getSeverity());
        c.setUrgency(req.getUrgency());
        c.setCustomer(customer);

        c.setPriorityScore(ruleService.computePriorityScore(c));
        return repo.save(c);
    }

    @Override
    public List<Complaint> getComplaintsForUser(User customer) {
        return repo.findByCustomer(customer);
    }

    @Override
    public List<Complaint> getPrioritizedComplaints() {
        return repo.findAllOrderByPriorityScoreDescCreatedAtAsc();
    }
}
