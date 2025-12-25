package com.example.demo.controller;

import com.example.demo.dto.ComplaintRequest;
import com.example.demo.entity.Complaint;
import com.example.demo.entity.User;
import com.example.demo.service.ComplaintService;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;
    private final UserService userService;

    public ComplaintController(ComplaintService complaintService,
                               UserService userService) {
        this.complaintService = complaintService;
        this.userService = userService;
    }

    // SUBMIT COMPLAINT
    @PostMapping("/submit")
    public ResponseEntity<Complaint> submitComplaint(
            @RequestParam Long userId,
            @RequestBody ComplaintRequest request) {

        User user = userService.findByEmail(
                userService.findByEmail(userId.toString()) != null
                        ? userId.toString()
                        : null
        );

        Complaint complaint = complaintService.submitComplaint(request, user);
        return ResponseEntity.ok(complaint);
    }

    // GET USER COMPLAINTS
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Complaint>> getUserComplaints(@PathVariable Long userId) {

        User user = new User();
        user.setId(userId);

        List<Complaint> complaints = complaintService.getComplaintsForUser(user);
        return ResponseEntity.ok(complaints);
    }

    // GET PRIORITIZED COMPLAINTS
    @GetMapping("/prioritized")
    public ResponseEntity<List<Complaint>> getPrioritizedComplaints() {
        return ResponseEntity.ok(complaintService.getPrioritizedComplaints());
    }

    // UPDATE STATUS
    @PutMapping("/status/{id}")
    public ResponseEntity<Complaint> updateStatus(
            @PathVariable Long id,
            @RequestParam Complaint.Status status) {

        Complaint complaint = complaintService.getComplaintById(id);
        if (complaint == null) {
            return ResponseEntity.notFound().build();
        }

        complaint.setStatus(status);
        Complaint saved = complaintService.saveComplaint(complaint);
        return ResponseEntity.ok(saved);
    }
}
