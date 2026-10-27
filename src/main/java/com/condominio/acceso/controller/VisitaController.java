package com.condominio.acceso.controller;

import com.condominio.acceso.dto.VisitaRequest;
import com.condominio.acceso.dto.VisitaResponse;
import com.condominio.acceso.service.VisitaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visitas")
@RequiredArgsConstructor
public class VisitaController {

    private final VisitaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VisitaResponse crear(@Valid @RequestBody VisitaRequest req) {
        return service.crear(req);
    }

    @GetMapping("/{id}")
    public VisitaResponse obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @GetMapping("/residente/{residenteId}")
    public List<VisitaResponse> porResidente(@PathVariable Long residenteId) {
        return service.listarPorResidente(residenteId);
    }

    @PatchMapping("/{id}/cancelar")
    public VisitaResponse cancelar(@PathVariable Long id) {
        return service.cancelar(id);
    }
}
