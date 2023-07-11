package com.api.hiring.hiring.controller;

import com.api.hiring.hiring.dto.CandidateDTO;
import com.api.hiring.hiring.dto.CandidateIdDTO;
import com.api.hiring.hiring.model.Candidate;
import com.api.hiring.hiring.service.CandidateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/v1/hiring")
public class CandidateController {

    @Autowired
    CandidateService service;

    @GetMapping("/status/candidate/{id}")
    public ResponseEntity<Map<String, String>> getStatus(@PathVariable Long id) {
        Candidate candidate = service.getStatus(id);
        Map<String, String> response = Collections.singletonMap("status", candidate.getStatus());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/approved")
    public  ResponseEntity<List<Candidate>> findApproved() {
        List candidates = service.findApproved();

        return new ResponseEntity<>(candidates, HttpStatus.OK);
    }

    @PostMapping("/start")
    public ResponseEntity<Map<String, Long>> startProcess(@RequestBody @Valid CandidateDTO candidate) {
        Candidate newCandidate = new Candidate(candidate.nome(), "Recebido");

        Candidate candidateAdded = service.startProcess(newCandidate);

        Map<String, Long> response = Collections.singletonMap("codCandidato", candidateAdded.getId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/schedule")
    public ResponseEntity scheduleInterview(@RequestBody @Valid CandidateIdDTO candidateId) {

        service.scheduleInterview(candidateId.codCandidato());

        return new ResponseEntity<> (HttpStatus.OK);
    }

    @PostMapping("/disqualify")
    public ResponseEntity disqualifyCandidate(@RequestBody @Valid CandidateIdDTO candidateId) {

        service.disqualifyCandidate(candidateId.codCandidato());

        return new ResponseEntity<> (HttpStatus.OK);
    }

    @PostMapping("/approve")
    public ResponseEntity approveCandidate(@RequestBody @Valid CandidateIdDTO candidateId) {

        service.approveCandidate(candidateId.codCandidato());

        return new ResponseEntity<> (HttpStatus.OK);
    }

}
