package com.example.demo.service;

import com.example.demo.entity.Complaint;
import com.example.demo.entity.ComplaintStatus;

public interface ComplaintStatusService {

    Complaint updateStatus(Long complaintId, ComplaintStatus status);

    Complaint getStatus(Long complaintId);
}
