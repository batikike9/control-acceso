package com.condominio.acceso.dto;

import com.condominio.acceso.model.TipoAcceso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ValidarAccesoRequest(@NotBlank String codigo, @NotNull TipoAcceso tipo) {
}
