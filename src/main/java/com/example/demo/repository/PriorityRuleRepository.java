package com.example.demo.repository;

import com.example.demo.entity.PriorityRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriorityRuleRepository extends JpaRepository<PriorityRule, Long> {

    List<PriorityRule> findByActiveTrue();
}
