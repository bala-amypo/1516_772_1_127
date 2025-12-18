package com.example.demo.repository;

import com.example.demo.entity.Complaint;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    List<Complaint> findByCustomer(User customer);

    @Query("select c from Complaint c order by c.priorityScore desc, c.createdAt asc")
    List<Complaint> findAllOrderByPriorityScoreDescCreatedAtAsc();
}
