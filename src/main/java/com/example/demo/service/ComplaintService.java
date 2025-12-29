package com.example.demo.service;

import com.example.demo.entity.Complaint;
import com.example.demo.entity.User;

import java.util.List;

public interface ComplaintService {

    Complaint submitComplaint(Complaint complaint, User user);

    Complaint getComplaintById(Long id);

    List<Complaint> getComplaintsByUser(User user);

    List<Complaint> getAllComplaints();

    Complaint saveComplaint(Complaint complaint);
}
