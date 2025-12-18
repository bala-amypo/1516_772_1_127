package com.example.demo.service;

import com.example.demo.entity.Complaint;
import com.example.demo.entity.User;

import java.util.List;

public interface ComplaintService {

    Complaint submitComplaint(Complaint complaint, User customer);

    List<Complaint> getComplaintsForUser(User customer);

    List<Complaint> getPrioritizedComplaints();

    Complaint getComplaintById(Long id);

    Complaint saveComplaint(Complaint complaint);
}
