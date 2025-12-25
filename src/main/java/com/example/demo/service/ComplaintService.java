package com.example.demo.service.impl;

import com.example.demo.dto.ComplaintRequest;
import com.example.demo.entity.Complaint;
import com.example.demo.entity.User;
import com.example.demo.repository.ComplaintRepository;
import com.example.demo.service.impl.PriorityRuleServiceImpl;

import java.util.List;

public class ComplaintServiceImpl {

    private final ComplaintRepository repo;
    private final PriorityRuleServiceImpl ruleService;

    public ComplaintServiceImpl(
            ComplaintRepository repo,
            Object ignored1,
            Object ignored2,
            PriorityRuleServiceImpl ruleService
    ) {
        this.repo = repo;
        this.ruleService = ruleService;
    }

    public Complaint submitComplaint(ComplaintRequest req, User user) {
        Complaint c = new Complaint();
        c.setTitle(req.getTitle());
        c.setDescription(req.getDescription());
        c.setCategory(req.getCategory());
        c.setChannel(req.getChannel());
        c.setSeverity(req.getSeverity());
        c.setUrgency(req.getUrgency());
        c.setCustomer(user);

        int score = ruleService.computePriorityScore(c);
        c.setPriorityScore(score);

        return repo.save(c);
    }

    public List<Complaint> getComplaintsForUser(User user) {
        return repo.findByCustomer(user);
    }

    public List<Complaint> getPrioritizedComplaints() {
        return repo.findAllOrderByPriorityScoreDescCreatedAtAsc();
    }
}
