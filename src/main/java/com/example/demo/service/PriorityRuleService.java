package com.example.demo.service;

import com.example.demo.entity.Complaint;
import com.example.demo.entity.PriorityRule;

import java.util.List;

public interface PriorityRuleService {
    int computePriorityScore(Complaint complaint);
    List<PriorityRule> getActiveRules();
}
