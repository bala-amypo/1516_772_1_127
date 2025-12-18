package com.example.demo.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
public class ComplaintStatus{
    @Id
    @GeneratedValue(Strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Complaint complaint;
    private String status;
    private LocalDateTime updatedOn;
    @Prepersist
    public void onUpdate(){
        updatedOn = LocalDateTime.now();
        
    }
}