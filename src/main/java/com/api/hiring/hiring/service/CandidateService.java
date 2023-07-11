package com.api.hiring.hiring.service;

import com.api.hiring.hiring.model.Candidate;
import com.api.hiring.hiring.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {

    @Autowired
    CandidateRepository repository;

    public Candidate startProcess(Candidate candidate) {

        return repository.save(candidate);
    }
}
