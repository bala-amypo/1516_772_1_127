package com.example.demo.repository;

import com.example.demo.entity.PriorityRule;
import java.util.List;
@Repository
public interface PriorityRuleRepository extends JpaRepository<PriorityRule, Long> {
    List<PriorityRule> findByActiveTrue();
}
