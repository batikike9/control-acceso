package com.condominio.acceso.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResidenteRequest(
        @NotBlank String nombre,
        String apellidos,
        @NotBlank @Email String email,
        String telefono,
        @NotNull Long unidadId) {
}
