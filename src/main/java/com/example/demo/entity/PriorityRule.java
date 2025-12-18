package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class PriorityRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleName;
    private String description;
    private Integer weight;

    private boolean active = true;

    @ManyToMany(mappedBy = "priorityRules")
    private List<Complaint> complaints;

    public Integer getWeight() {
        return weight;
    }

    public boolean isActive() {
        return active;
    }
}
