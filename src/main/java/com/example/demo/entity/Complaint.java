package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Complaint {

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

    private int priorityScore;

    private LocalDateTime createdOn;

    @ManyToOne
    private User user;

    // ===== ENUMS =====
    public enum Severity {
        LOW, MEDIUM, HIGH
    }

    public enum Urgency {
        LOW, MEDIUM, HIGH
    }

    // ===== GETTERS & SETTERS =====
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

    public int getPriorityScore() { return priorityScore; }

    public void setPriorityScore(int priorityScore) {
        this.priorityScore = priorityScore;
    }

    public LocalDateTime getCreatedOn() { return createdOn; }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }
}
