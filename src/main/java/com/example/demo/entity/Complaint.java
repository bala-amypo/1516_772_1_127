package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Complaint {

    public enum Severity { LOW, MEDIUM, HIGH, CRITICAL }
    public enum Urgency { LOW, MEDIUM, HIGH, IMMEDIATE }
    public enum Status { NEW, IN_PROGRESS, RESOLVED, CLOSED }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String category;
    private String channel;

    @Enumerated(EnumType.STRING)
    private Severity severity;

    @Enumerated(EnumType.STRING)
    private Urgency urgency;

    @Enumerated(EnumType.STRING)
    private Status status = Status.NEW;

    private Integer priorityScore = 0;

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    private User customer;

    @ManyToOne
    private User assignedAgent;

    @ManyToMany
    @JoinTable(
            name = "complaint_rules",
            joinColumns = @JoinColumn(name = "complaint_id"),
            inverseJoinColumns = @JoinColumn(name = "rule_id")
    )
    private Set<PriorityRule> priorityRules = new HashSet<>();

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getChannel() { return channel; }
    public void setChannel(String channel) { this.channel = channel; }

    public Severity getSeverity() { return severity; }
    public void setSeverity(Severity severity) { this.severity = severity; }

    public Urgency getUrgency() { return urgency; }
    public void setUrgency(Urgency urgency) { this.urgency = urgency; }

    public Status getStatus() { return status; }

    public Integer getPriorityScore() { return priorityScore; }
    public void setPriorityScore(Integer priorityScore) { this.priorityScore = priorityScore; }

    public User getCustomer() { return customer; }
    public void setCustomer(User customer) { this.customer = customer; }

    public User getAssignedAgent() { return assignedAgent; }
    public void setAssignedAgent(User assignedAgent) { this.assignedAgent = assignedAgent; }

    public Set<PriorityRule> getPriorityRules() { return priorityRules; }
}
