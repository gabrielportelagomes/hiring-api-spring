package com.api.hiring.hiring.controller;

import com.api.hiring.hiring.dto.CandidateDTO;
import com.api.hiring.hiring.dto.CandidateIdDTO;
import com.api.hiring.hiring.model.Candidate;
import com.api.hiring.hiring.service.CandidateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CandidateControllerTest {

    @InjectMocks
    private CandidateController candidateController;

    @Mock
    private CandidateService candidateService;

    private CandidateDTO candidateDTO;

    private Candidate candidate;

    @BeforeEach
    void setup() {
        candidateDTO = new CandidateDTO("Teste");
        candidate = new Candidate(candidateDTO.nome(), "Recebido");
    }

    @Test
    void shouldGetStatus() {
        Long candidateId = candidate.getId();

        when(candidateService.getStatus(candidateId)).thenReturn(candidate);
        Map<String, String> responseBody = Collections.singletonMap("status", candidate.getStatus());
        ResponseEntity<Map<String, String>> expectedResponse = ResponseEntity.status(HttpStatus.OK).body(responseBody);

        ResponseEntity<Map<String, String>> response = assertDoesNotThrow(() -> candidateController.getStatus(candidateId));

        assertNotNull(response);
        assertEquals(expectedResponse, response);
    }

    @Test
    void shouldFindApproved() {
        List candidates = new ArrayList();
        candidates.add(new Candidate(2L, "Candidato 2", "Aprovado"));
        candidates.add(new Candidate(5L, "Candidato 5", "Aprovado"));
        candidates.add(new Candidate(6L, "Candidato 6", "Aprovado"));

        when(candidateService.findApproved()).thenReturn(candidates);

        ResponseEntity<List<Candidate>> response = candidateController.findApproved();

        assertNotNull(response);
        assertEquals(ResponseEntity.status(HttpStatus.OK).body(candidates), response);
    }

    @Test
    void shouldStartProcess() {
        when(candidateService.startProcess(candidate)).thenReturn(candidate);
        Map<String, Long> responseBody = Collections.singletonMap("codCandidato", candidate.getId());
        ResponseEntity<Map<String, Long>> expectedResponse = ResponseEntity.status(HttpStatus.CREATED).body(responseBody);

        ResponseEntity<Map<String, Long>> response = assertDoesNotThrow(() -> candidateController.startProcess(candidateDTO));

        assertNotNull(response);
        assertEquals(expectedResponse, response);
    }

    @Test
    void shouldScheduleInterview() {
        Long candidateId = candidate.getId();
        CandidateIdDTO candidateIdDTO = new CandidateIdDTO(candidateId);
        var response = assertDoesNotThrow(() ->candidateController.scheduleInterview(candidateIdDTO));

        assertEquals(ResponseEntity.status(HttpStatus.OK).build(), response);
    }

    @Test
    void shouldDisqualifyCandidate() {
        Long candidateId = candidate.getId();
        CandidateIdDTO candidateIdDTO = new CandidateIdDTO(candidateId);
        var response = assertDoesNotThrow(() ->candidateController.disqualifyCandidate(candidateIdDTO));

        assertEquals(ResponseEntity.status(HttpStatus.OK).build(), response);
    }

    @Test
    void shouldApproveCandidate() {
        Long candidateId = candidate.getId();
        CandidateIdDTO candidateIdDTO = new CandidateIdDTO(candidateId);
        var response = assertDoesNotThrow(() ->candidateController.approveCandidate(candidateIdDTO));

        assertEquals(ResponseEntity.status(HttpStatus.OK).build(), response);
    }
}
