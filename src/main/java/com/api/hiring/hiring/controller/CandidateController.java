package com.api.hiring.hiring.controller;

import com.api.hiring.hiring.dto.CandidateDTO;
import com.api.hiring.hiring.model.Candidate;
import com.api.hiring.hiring.service.CandidateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/v1/hiring")
public class CandidateController {

    @Autowired
    CandidateService service;

    @PostMapping("/start")
    public ResponseEntity<Map<String, Long>> startProcess(@RequestBody @Valid CandidateDTO candidate) {
        Candidate newCandidate = new Candidate(candidate.nome(), "Recebido");

        Candidate candidateAdded = service.startProcess(newCandidate);

        Map<String, Long> response = Collections.singletonMap("codCandidato", candidateAdded.getId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
