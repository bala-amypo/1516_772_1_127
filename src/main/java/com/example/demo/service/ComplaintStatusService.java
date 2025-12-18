package com.example.demo.service;

import com.example.demo.entity.Complaint;

public interface ComplaintStatusService {

    Complaint updateStatus(Long complaintId, Complaint.Status status);

    Complaint getStatus(Long complaintId);
}
