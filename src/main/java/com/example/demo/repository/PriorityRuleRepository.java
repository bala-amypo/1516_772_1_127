package com.example.demo.repository;

import com.example.demo.entity.PriorityRule;
import java.util.List;

public interface PriorityRuleRepository {

    List<PriorityRule> findByActiveTrue();
}
