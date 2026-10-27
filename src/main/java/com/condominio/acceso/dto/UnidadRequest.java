package com.condominio.acceso.dto;

import jakarta.validation.constraints.NotBlank;

public record UnidadRequest(String torre, @NotBlank String numero) {
}
