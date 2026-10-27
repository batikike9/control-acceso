package com.condominio.acceso.service;

import com.condominio.acceso.dto.ResidenteRequest;
import com.condominio.acceso.dto.ResidenteResponse;
import com.condominio.acceso.exception.RecursoNoEncontradoException;
import com.condominio.acceso.exception.ReglaNegocioException;
import com.condominio.acceso.model.Residente;
import com.condominio.acceso.repository.ResidenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResidenteService {

    private final ResidenteRepository repo;
    private final UnidadService unidadService;

    public ResidenteResponse crear(ResidenteRequest req) {
        if (repo.existsByEmail(req.email())) {
            throw new ReglaNegocioException("Ya existe un residente con ese correo");
        }
        Residente r = new Residente();
        aplicar(r, req);
        return ResidenteResponse.from(repo.save(r));
    }

    public List<ResidenteResponse> listar() {
        return repo.findAll().stream().map(ResidenteResponse::from).toList();
    }

    public ResidenteResponse obtener(Long id) {
        return ResidenteResponse.from(buscar(id));
    }

    public ResidenteResponse actualizar(Long id, ResidenteRequest req) {
        Residente r = buscar(id);
        aplicar(r, req);
        return ResidenteResponse.from(repo.save(r));
    }

    public void desactivar(Long id) {
        Residente r = buscar(id);
        r.setActivo(false);
        repo.save(r);
    }

    public Residente buscar(Long id) {
        return repo.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Residente", id));
    }

    private void aplicar(Residente r, ResidenteRequest req) {
        r.setNombre(req.nombre());
        r.setApellidos(req.apellidos());
        r.setEmail(req.email());
        r.setTelefono(req.telefono());
        r.setUnidad(unidadService.buscar(req.unidadId()));
    }
}
