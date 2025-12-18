package com.example.demo.entity;
import jakarta.persistence.*;
@Entity
public class PriortyRule{
    @Id
    @GeneratedValue(Strategy= GenerationType.IDENTITY)
    private Long id;
    private String categry;
    private Integer baseScore;
    private String description;
}