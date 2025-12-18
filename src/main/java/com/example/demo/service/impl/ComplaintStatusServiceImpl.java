package com.example.demo.service.impl;

import com.example.demo.entity.Complaint;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ComplaintRepository;
import com.example.demo.service.ComplaintStatusService;
import org.springframework.stereotype.Service;

@Service
public class ComplaintStatusServiceImpl implements ComplaintStatusService {

    private final ComplaintRepository complaintRepository;

    public ComplaintStatusServiceImpl(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    @Override
    public Complaint updateStatus(Long complaintId, Complaint.Status status) {
        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint not found"));

        complaint.setStatus(status);   // ✅ SAME ENUM TYPE
        return complaintRepository.save(complaint);
    }

    @Override
    public Complaint getStatus(Long complaintId) {
        return complaintRepository.findById(complaintId)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint not found"));
    }
}
