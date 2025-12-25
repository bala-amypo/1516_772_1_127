package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ComplaintStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Complaint complaint;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime updatedOn;

    public enum Status {
        OPEN, IN_PROGRESS, RESOLVED
    }

    @PrePersist
    public void onUpdate() {
        this.updatedOn = LocalDateTime.now();
    }

    public Status getStatus() {
        return status;
    }
    
}
