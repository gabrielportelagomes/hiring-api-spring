package com.api.hiring.hiring.repository;

import com.api.hiring.hiring.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    List<Candidate> findByStatus(String status);
}
