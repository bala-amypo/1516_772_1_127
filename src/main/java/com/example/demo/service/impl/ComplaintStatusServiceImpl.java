package com.example.demo.service.impl;

import com.example.demo.entity.Complaint;
import com.example.demo.repository.ComplaintRepository;
import org.springframework.stereotype.Service;

@Service
public class ComplaintStatusServiceImpl {

    private final ComplaintRepository complaintRepository;

    public ComplaintStatusServiceImpl(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    public Complaint updateStatus(Long complaintId, Complaint.Status status) {

        Complaint complaint = complaintRepository.findById(complaintId)
                .orElse(null);

        if (complaint == null) {
            return null;
        }

        complaint.setStatus(status);
        return complaintRepository.save(complaint);
    }
}
