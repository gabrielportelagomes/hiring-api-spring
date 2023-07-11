package com.api.hiring.hiring.repository;

import com.api.hiring.hiring.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    Candidate findByName(String name);
}
