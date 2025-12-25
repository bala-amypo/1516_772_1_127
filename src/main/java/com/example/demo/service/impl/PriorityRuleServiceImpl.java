package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.PriorityRuleRepository;
import com.example.demo.service.PriorityRuleService;

public class PriorityRuleServiceImpl implements PriorityRuleService {

    private final PriorityRuleRepository repo;

    public PriorityRuleServiceImpl(PriorityRuleRepository repo) {
        this.repo = repo;
    }

    @Override
    public int computePriorityScore(Complaint complaint) {
        int score = 0;

        if (complaint.getSeverity() != null) {
            score += complaint.getSeverity().ordinal() + 1;
        }
        if (complaint.getUrgency() != null) {
            score += complaint.getUrgency().ordinal() + 1;
        }

        for (PriorityRule rule : repo.findByActiveTrue()) {
            score += rule.getWeight();
            complaint.getPriorityRules().add(rule);
        }
        return score;
    }

    @Override
    public java.util.List<PriorityRule> getActiveRules() {
        return repo.findByActiveTrue();
    }
}
