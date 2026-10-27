package com.condominio.acceso.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record VisitaRequest(
        @NotBlank String nombreVisitante,
        String motivo,
        @NotNull Long residenteId,
        @NotNull LocalDateTime vigenteDesde,
        @NotNull LocalDateTime vigenteHasta) {
}
