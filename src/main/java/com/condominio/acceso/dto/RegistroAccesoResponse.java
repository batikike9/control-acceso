package com.condominio.acceso.dto;

import com.condominio.acceso.model.RegistroAcceso;
import com.condominio.acceso.model.ResultadoAcceso;
import com.condominio.acceso.model.TipoAcceso;

import java.time.LocalDateTime;

public record RegistroAccesoResponse(Long id, String visitante, String codigo, TipoAcceso tipo,
                                     ResultadoAcceso resultado, String motivoDenegacion,
                                     LocalDateTime fechaHora) {
    public static RegistroAccesoResponse from(RegistroAcceso r) {
        return new RegistroAccesoResponse(r.getId(),
                r.getVisita() != null ? r.getVisita().getNombreVisitante() : null,
                r.getCodigoIntentado(), r.getTipo(), r.getResultado(),
                r.getMotivoDenegacion(), r.getFechaHora());
    }
}
