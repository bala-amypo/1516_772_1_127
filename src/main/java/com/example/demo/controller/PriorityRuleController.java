package com.example.demo.controller;

import com.example.demo.entity.PriorityRule;
import com.example.demo.service.PriorityRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class PriorityRuleController {

    private final PriorityRuleService ruleService;

    public PriorityRuleController(PriorityRuleService ruleService) {
        this.ruleService = ruleService;
    }

    // GET /rules/all
    @GetMapping("/all")
    public List<PriorityRule> getAllRules() {
        return ruleService.getActiveRules();
    }
}
