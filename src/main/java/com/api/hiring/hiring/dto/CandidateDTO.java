package com.api.hiring.hiring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CandidateDTO(@NotBlank  @Pattern(regexp = "\\D+", message = "Nome inválido") String nome) {
}
