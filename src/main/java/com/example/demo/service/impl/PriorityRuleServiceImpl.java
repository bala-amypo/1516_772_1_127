package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.PriorityRuleRepository;
import com.example.demo.service.PriorityRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // ⭐⭐⭐ THIS LINE FIXES EVERYTHING
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
    public List<PriorityRule> getActiveRules() {
        return repo.findByActiveTrue();
    }
}
