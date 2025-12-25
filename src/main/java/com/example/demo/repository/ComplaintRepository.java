package com.example.demo.repository;

import com.example.demo.entity.Complaint;
import com.example.demo.entity.User;
import java.util.List;

public interface ComplaintRepository {

    Complaint save(Complaint complaint);

    List<Complaint> findByCustomer(User user);

    List<Complaint> findAllOrderByPriorityScoreDescCreatedAtAsc();
}
