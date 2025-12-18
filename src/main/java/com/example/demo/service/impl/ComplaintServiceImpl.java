package com.example.demo.service.impl;

import com.example.demo.dto.ComplaintRequest;
import com.example.demo.entity.Complaint;
import com.example.demo.entity.User;
import com.example.demo.repository.ComplaintRepository;
import com.example.demo.service.ComplaintService;
import com.example.demo.service.PriorityRuleService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository repo;
    private final PriorityRuleService ruleService;
    private final UserService userService;

    public ComplaintServiceImpl(ComplaintRepository repo,
                                PriorityRuleService ruleService,
                                UserService userService) {
        this.repo = repo;
        this.ruleService = ruleService;
        this.userService = userService;
    }

    @Override
    public Complaint submitComplaint(ComplaintRequest r, User customer) {
        Complaint c = new Complaint();
        c.setCustomer(customer);
        c.setSeverity(r.severity);
        c.setUrgency(r.urgency);

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
