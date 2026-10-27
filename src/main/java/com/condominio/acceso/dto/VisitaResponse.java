package com.condominio.acceso.dto;

import com.condominio.acceso.model.EstadoVisita;
import com.condominio.acceso.model.Visita;

import java.time.LocalDateTime;

public record VisitaResponse(Long id, String nombreVisitante, String motivo, Long residenteId,
                             String codigoAcceso, LocalDateTime vigenteDesde,
                             LocalDateTime vigenteHasta, EstadoVisita estado) {
    public static VisitaResponse from(Visita v) {
        return new VisitaResponse(v.getId(), v.getNombreVisitante(), v.getMotivo(),
                v.getAnfitrion().getId(), v.getCodigoAcceso(), v.getVigenteDesde(),
                v.getVigenteHasta(), v.getEstado());
    }
}
