package com.api.hiring.hiring.service;

import com.api.hiring.hiring.exception.NotFoundException;
import com.api.hiring.hiring.model.Candidate;
import com.api.hiring.hiring.repository.CandidateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CandidateServiceTest {

    @InjectMocks
    private CandidateService candidateService;

    @Mock
    private CandidateRepository candidateRepository;

    private Candidate candidate;

    @BeforeEach
    void setup() {
        candidate = new Candidate("Teste", "Recebido");
    }

    @Test
    void shouldGetStatus() {
        Long candidateId = candidate.getId();

        when(candidateRepository.findById(candidateId)).thenReturn(Optional.ofNullable(candidate));
        var response = assertDoesNotThrow(() -> candidateService.getStatus(candidateId));
        assertNotNull(response);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenGetStatus() {
        Long candidateId = 2L;

        when(candidateRepository.findById(candidateId)).thenReturn(Optional.empty());
        var exception = assertThrows(NotFoundException.class, () -> candidateService.getStatus(candidateId));
        assertEquals("Candidato não encontrado", exception.getMessage());
    }

    @Test
    void shouldFindApproved() {
        List candidates = new ArrayList();
        candidates.add(new Candidate(2L, "Candidato 2", "Aprovado"));
        candidates.add(new Candidate(5L, "Candidato 5", "Aprovado"));
        candidates.add(new Candidate(6L, "Candidato 6", "Aprovado"));

        when(candidateRepository.findByStatus("Aprovado")).thenReturn(candidates);

        var response = assertDoesNotThrow(() -> candidateService.findApproved());
        assertNotNull(response);
    }

    @Test
    void shouldStartProcess() {
        when(candidateRepository.save(candidate)).thenReturn(candidate);
        var response = assertDoesNotThrow(() -> candidateService.startProcess(candidate));
        assertNotNull(response);
    }

    @Test
    void shouldThrowDataIntegrityViolationException() {
        Candidate newCandidate = new Candidate("Teste", "Recebido");

        when(candidateRepository.save(newCandidate)).thenThrow(DataIntegrityViolationException.class);
        assertThrows(DataIntegrityViolationException.class, () -> candidateService.startProcess(newCandidate));
    }

    @Test
    void shouldScheduleInterview() {
        Long candidateId = candidate.getId();

        when(candidateRepository.findById(candidateId)).thenReturn(Optional.ofNullable(candidate));
        assertDoesNotThrow(() -> candidateService.scheduleInterview(candidateId));
    }

    @Test
    void shouldThrowNotFoundExceptionWhenScheduleInterview() {
        Long candidateId = 2L;

        when(candidateRepository.findById(candidateId)).thenReturn(Optional.empty());
        var exception =  assertThrows(NotFoundException.class, () -> candidateService.scheduleInterview(candidateId));
        assertEquals("Candidato não encontrado", exception.getMessage());
    }

    @Test
    void shouldThrowNotFoundExceptionWhenScheduleInterviewWithWrongStatus() {
        Candidate newCandidate = new Candidate("Teste", "Aprovado");
        Long candidateId = newCandidate.getId();

        when(candidateRepository.findById(candidateId)).thenReturn(Optional.ofNullable(newCandidate));
        var exception = assertThrows(NotFoundException.class, () -> candidateService.scheduleInterview(candidateId));
        assertEquals("Candidato não encontrado", exception.getMessage());
    }

    @Test
    void shouldDisqualifyCandidate() {
        Long candidateId = candidate.getId();

        when(candidateRepository.findById(candidateId)).thenReturn(Optional.ofNullable(candidate));
        assertDoesNotThrow(() -> candidateService.disqualifyCandidate(candidateId));
    }

    @Test
    void shouldThrowNotFoundExceptionWhenDisqualifyCandidate() {
        Long candidateId = 2L;

        when(candidateRepository.findById(candidateId)).thenReturn(Optional.empty());
        var exception =  assertThrows(NotFoundException.class, () -> candidateService.disqualifyCandidate(candidateId));
        assertEquals("Candidato não encontrado", exception.getMessage());
    }

    @Test
    void shouldApproveCandidate() {
        Candidate newCandidate = new Candidate("Teste", "Qualificado");
        Long candidateId = newCandidate.getId();

        when(candidateRepository.findById(candidateId)).thenReturn(Optional.ofNullable(newCandidate));
        assertDoesNotThrow(() -> candidateService.approveCandidate(candidateId));
    }

    @Test
    void shouldThrowNotFoundExceptionWhenApproveCandidate() {
        Long candidateId = 2L;

        when(candidateRepository.findById(candidateId)).thenReturn(Optional.empty());
        var exception =  assertThrows(NotFoundException.class, () -> candidateService.approveCandidate(candidateId));
        assertEquals("Candidato não encontrado", exception.getMessage());
    }

    @Test
    void shouldThrowNotFoundExceptionWhenApproveCandidatewWithWrongStatus() {
        Candidate newCandidate = new Candidate("Teste", "Recebido");
        Long candidateId = newCandidate.getId();

        when(candidateRepository.findById(candidateId)).thenReturn(Optional.ofNullable(newCandidate));
        var exception = assertThrows(NotFoundException.class, () -> candidateService.approveCandidate(candidateId));
        assertEquals("Candidato não encontrado", exception.getMessage());
    }
}
