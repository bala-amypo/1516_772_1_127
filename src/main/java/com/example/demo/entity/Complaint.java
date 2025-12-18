package com.example.demo.entity;
import jakarta.peristence.*;
import java.time.LocalDateTime;
@Entity
public class Complaint{
    @Id
    @GenerateValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String category;
    private int priorityScore;
    
    private LocalDateTime submittedOn;
    @ManyToOne
    private User user;
    @PrePersist
    public void onCreate(){
        submittedOn = LocalDateTime.now();
    }
}