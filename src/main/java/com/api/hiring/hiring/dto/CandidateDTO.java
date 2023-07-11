package com.api.hiring.hiring.dto;

import jakarta.validation.constraints.NotBlank;

public record CandidateDTO(@NotBlank String nome) {
}
