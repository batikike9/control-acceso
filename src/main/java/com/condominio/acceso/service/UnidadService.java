package com.condominio.acceso.service;

import com.condominio.acceso.dto.UnidadRequest;
import com.condominio.acceso.dto.UnidadResponse;
import com.condominio.acceso.exception.RecursoNoEncontradoException;
import com.condominio.acceso.model.Unidad;
import com.condominio.acceso.repository.UnidadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnidadService {

    private final UnidadRepository repo;

    public UnidadResponse crear(UnidadRequest req) {
        Unidad u = new Unidad();
        u.setTorre(req.torre());
        u.setNumero(req.numero());
        return UnidadResponse.from(repo.save(u));
    }

    public List<UnidadResponse> listar() {
        return repo.findAll().stream().map(UnidadResponse::from).toList();
    }

    public UnidadResponse obtener(Long id) {
        return UnidadResponse.from(buscar(id));
    }

    public Unidad buscar(Long id) {
        return repo.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Unidad", id));
    }
}
