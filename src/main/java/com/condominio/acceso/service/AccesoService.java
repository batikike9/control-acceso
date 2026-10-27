package com.condominio.acceso.service;

import com.condominio.acceso.dto.AccesoResponse;
import com.condominio.acceso.dto.RegistroAccesoResponse;
import com.condominio.acceso.dto.ValidarAccesoRequest;
import com.condominio.acceso.model.*;
import com.condominio.acceso.repository.RegistroAccesoRepository;
import com.condominio.acceso.repository.VisitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccesoService {

    private final VisitaRepository visitaRepo;
    private final RegistroAccesoRepository registroRepo;

    /** Valida el código en caseta y registra el intento (permitido o denegado). */
    @Transactional
    public AccesoResponse validar(ValidarAccesoRequest req) {
        Visita visita = visitaRepo.findByCodigoAcceso(req.codigo().trim().toUpperCase()).orElse(null);

        String motivo = motivoDenegacion(visita, req.tipo());
        RegistroAcceso reg = new RegistroAcceso();
        reg.setVisita(visita);
        reg.setCodigoIntentado(req.codigo());
        reg.setTipo(req.tipo());

        if (motivo != null) {
            reg.setResultado(ResultadoAcceso.DENEGADO);
            reg.setMotivoDenegacion(motivo);
        } else {
            reg.setResultado(ResultadoAcceso.PERMITIDO);
            visita.setEstado(req.tipo() == TipoAcceso.ENTRADA ? EstadoVisita.DENTRO : EstadoVisita.FINALIZADA);
            visitaRepo.save(visita);
        }
        reg = registroRepo.save(reg);

        return new AccesoResponse(reg.getId(), reg.getResultado(), reg.getTipo(),
                motivo != null ? motivo : "Acceso " + req.tipo().name().toLowerCase() + " permitido",
                visita != null ? visita.getNombreVisitante() : null, reg.getFechaHora());
    }

    public List<RegistroAccesoResponse> historial(LocalDateTime desde, LocalDateTime hasta) {
        return registroRepo.findByFechaHoraBetweenOrderByFechaHoraDesc(desde, hasta)
                .stream().map(RegistroAccesoResponse::from).toList();
    }

    /** @return null si el acceso es válido, o el motivo de denegación. */
    private String motivoDenegacion(Visita v, TipoAcceso tipo) {
        if (v == null) return "Código inexistente";
        if (v.getEstado() == EstadoVisita.CANCELADA) return "Visita cancelada";
        if (v.getEstado() == EstadoVisita.FINALIZADA) return "Código ya utilizado";
        if (!v.getAnfitrion().isActivo()) return "Residente anfitrión inactivo";
        if (tipo == TipoAcceso.ENTRADA) {
            if (v.getEstado() == EstadoVisita.DENTRO) return "El visitante ya está dentro";
            LocalDateTime ahora = LocalDateTime.now();
            if (ahora.isBefore(v.getVigenteDesde()) || ahora.isAfter(v.getVigenteHasta())) {
                return "Fuera del horario de vigencia";
            }
        } else if (v.getEstado() != EstadoVisita.DENTRO) {
            return "No hay entrada registrada para este código";
        }
        return null;
    }
}
