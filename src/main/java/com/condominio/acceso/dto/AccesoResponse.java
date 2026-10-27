package com.condominio.acceso.dto;

import com.condominio.acceso.model.ResultadoAcceso;
import com.condominio.acceso.model.TipoAcceso;

import java.time.LocalDateTime;

public record AccesoResponse(Long registroId, ResultadoAcceso resultado, TipoAcceso tipo,
                             String mensaje, String visitante, LocalDateTime fechaHora) {
}
