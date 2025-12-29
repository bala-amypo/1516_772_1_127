package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "complaints")
public class Complaint {

    // ---------------- ENUMS ----------------

    public enum Status {
        NEW, OPEN, IN_PROGRESS, RESOLVED
    }

    public enum Severity {
        LOW, MEDIUM, HIGH, CRITICAL
    }

    public enum Urgency {
        LOW, MEDIUM, HIGH, IMMEDIATE
    }

    // ---------------- FIELDS ----------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2000)
    private String description;

    private String category;

    private String channel;

    private Integer priorityScore;

    @Enumerated(EnumType.STRING)
    private Status status = Status.NEW;

    @Enumerated(EnumType.STRING)
    private Severity severity;

    @Enumerated(EnumType.STRING)
    private Urgency urgency;

    private LocalDateTime createdAt;

    // ---------------- RELATIONSHIPS ----------------

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private User assignedAgent;

    @ManyToMany
    @JoinTable(
            name = "complaint_priority_rules",
            joinColumns = @JoinColumn(name = "complaint_id"),
            inverseJoinColumns = @JoinColumn(name = "rule_id")
    )
    private Set<PriorityRule> priorityRules = new HashSet<>();

    // ---------------- JPA CALLBACK ----------------

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // ---------------- GETTERS & SETTERS ----------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Integer getPriorityScore() {
        return priorityScore;
    }

    public void setPriorityScore(Integer priorityScore) {
        this.priorityScore = priorityScore;
    }

    // ⭐ VERY IMPORTANT (FIXES YOUR ERROR)
    public Status getStatus() {
        return status;
    }

    // ⭐ THIS METHOD WAS MISSING — NOW FIXED
    public void setStatus(Status status) {
        this.status = status;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public Urgency getUrgency() {
        return urgency;
    }

    public void setUrgency(Urgency urgency) {
        this.urgency = urgency;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getAssignedAgent() {
        return assignedAgent;
    }

    public void setAssignedAgent(User assignedAgent) {
        this.assignedAgent = assignedAgent;
    }

    public Set<PriorityRule> getPriorityRules() {
        return priorityRules;
    }

    public void setPriorityRules(Set<PriorityRule> priorityRules) {
        this.priorityRules = priorityRules;
    }
}
