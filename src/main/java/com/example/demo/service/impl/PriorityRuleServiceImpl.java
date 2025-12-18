package com.example.demo.service.impl;

import com.example.demo.entity.Complaint;
import com.example.demo.entity.PriorityRule;
import com.example.demo.repository.PriorityRuleRepository;
import com.example.demo.service.PriorityRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriorityRuleServiceImpl implements PriorityRuleService {

    private final PriorityRuleRepository repo;

    public PriorityRuleServiceImpl(PriorityRuleRepository repo) {
        this.repo = repo;
    }

    @Override
    public int computePriorityScore(Complaint c) {
        int score = 0;

        switch (c.getSeverity()) {
            case LOW -> score += 1;
            case MEDIUM -> score += 3;
            case HIGH -> score += 5;
            case CRITICAL -> score += 10;
        }

        switch (c.getUrgency()) {
            case LOW -> score += 1;
            case MEDIUM -> score += 3;
            case HIGH -> score += 5;
            case IMMEDIATE -> score += 10;
        }

        for (PriorityRule rule : repo.findByActiveTrue()) {
            score += rule.getWeight();
        }

        return score;
    }

    @Override
    public List<PriorityRule> getActiveRules() {
        return repo.findByActiveTrue();
    }
}
