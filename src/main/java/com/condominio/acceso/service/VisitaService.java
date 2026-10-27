package com.condominio.acceso.service;

import com.condominio.acceso.dto.VisitaRequest;
import com.condominio.acceso.dto.VisitaResponse;
import com.condominio.acceso.exception.RecursoNoEncontradoException;
import com.condominio.acceso.exception.ReglaNegocioException;
import com.condominio.acceso.model.EstadoVisita;
import com.condominio.acceso.model.Residente;
import com.condominio.acceso.model.Visita;
import com.condominio.acceso.repository.VisitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VisitaService {

    private final VisitaRepository repo;
    private final ResidenteService residenteService;

    public VisitaResponse crear(VisitaRequest req) {
        if (!req.vigenteHasta().isAfter(req.vigenteDesde())) {
            throw new ReglaNegocioException("La vigencia final debe ser posterior a la inicial");
        }
        Residente anfitrion = residenteService.buscar(req.residenteId());
        if (!anfitrion.isActivo()) {
            throw new ReglaNegocioException("El residente está inactivo");
        }
        Visita v = new Visita();
        v.setNombreVisitante(req.nombreVisitante());
        v.setMotivo(req.motivo());
        v.setAnfitrion(anfitrion);
        v.setVigenteDesde(req.vigenteDesde());
        v.setVigenteHasta(req.vigenteHasta());
        v.setCodigoAcceso(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        return VisitaResponse.from(repo.save(v));
    }

    public List<VisitaResponse> listarPorResidente(Long residenteId) {
        return repo.findByAnfitrionId(residenteId).stream().map(VisitaResponse::from).toList();
    }

    public VisitaResponse obtener(Long id) {
        return VisitaResponse.from(buscar(id));
    }

    public VisitaResponse cancelar(Long id) {
        Visita v = buscar(id);
        if (v.getEstado() != EstadoVisita.PENDIENTE) {
            throw new ReglaNegocioException("Solo se pueden cancelar visitas pendientes");
        }
        v.setEstado(EstadoVisita.CANCELADA);
        return VisitaResponse.from(repo.save(v));
    }

    private Visita buscar(Long id) {
        return repo.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Visita", id));
    }
}
