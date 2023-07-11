package com.api.hiring.hiring.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

public record CandidateIdDTO(@NotNull  @Digits(integer = 10, fraction = 0, message = "Código inválido") Long codCandidato) {
}
