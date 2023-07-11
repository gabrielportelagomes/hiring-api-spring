package com.api.hiring.hiring.service;

import com.api.hiring.hiring.exception.NotFoundException;
import com.api.hiring.hiring.model.Candidate;
import com.api.hiring.hiring.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {

    @Autowired
    CandidateRepository repository;

    public Candidate getStatus(Long id) {
        Candidate candidate = repository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Candidato não encontrado");
        });

        return  candidate;
    }

    public Candidate startProcess(Candidate candidate) {

        return repository.save(candidate);
    }

    public void scheduleInterview(Long id) {
        Candidate candidate = repository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Candidato não encontrado");
        });

        if(!candidate.getStatus().equals("Recebido")) {
            throw new NotFoundException("Candidato não encontrado");
        }

        candidate.setStatus("Qualificado");
        repository.save(candidate);
    }

    public void disqualifyCandidate(Long id) {
        Candidate candidate = repository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Candidato não encontrado");
        });

        repository.delete(candidate);
    }

    public void approveCandidate(Long id) {
        Candidate candidate = repository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Candidato não encontrado");
        });

        if(!candidate.getStatus().equals("Qualificado")) {
            throw new NotFoundException("Candidato não encontrado");
        }

        candidate.setStatus("Aprovado");
        repository.save(candidate);
    }
}
