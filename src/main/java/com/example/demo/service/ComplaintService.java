package com.example.demo.service;

import com.example.demo.dto.ComplaintRequest;
import com.example.demo.entity.Complaint;
import com.example.demo.entity.User;

import java.util.List;

public interface ComplaintService {

    Complaint submitComplaint(ComplaintRequest request, User customer);

    Complaint saveComplaint(Complaint complaint);

    Complaint getComplaintById(Long id);

    List<Complaint> getComplaintsForUser(User customer);

    List<Complaint> getPrioritizedComplaints();
}
