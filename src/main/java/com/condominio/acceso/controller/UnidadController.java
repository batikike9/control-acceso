package com.condominio.acceso.controller;

import com.condominio.acceso.dto.UnidadRequest;
import com.condominio.acceso.dto.UnidadResponse;
import com.condominio.acceso.service.UnidadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/unidades")
@RequiredArgsConstructor
public class UnidadController {

    private final UnidadService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UnidadResponse crear(@Valid @RequestBody UnidadRequest req) {
        return service.crear(req);
    }

    @GetMapping
    public List<UnidadResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public UnidadResponse obtener(@PathVariable Long id) {
        return service.obtener(id);
    }
}
