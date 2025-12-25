package com.example.demo.repository;

import com.example.demo.entity.Complaint;
import com.example.demo.entity.User;

import java.util.List;
import java.util.Optional;

public interface ComplaintRepository {

    Complaint save(Complaint complaint);

    Optional<Complaint> findById(Long id);

    List<Complaint> findByCustomer(User user);

    List<Complaint> findAllOrderByPriorityScoreDescCreatedAtAsc();
}
