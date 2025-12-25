package com.example.demo.repository;

import com.example.demo.entity.Complaint;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    List<Complaint> findByCustomer(User customer);

    @Query("SELECT c FROM Complaint c ORDER BY c.priorityScore DESC, c.createdAt ASC")
    List<Complaint> findAllOrderByPriorityScoreDescCreatedAtAsc();
}
