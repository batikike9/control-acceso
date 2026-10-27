package com.condominio.acceso.controller;

import com.condominio.acceso.dto.ResidenteRequest;
import com.condominio.acceso.dto.ResidenteResponse;
import com.condominio.acceso.service.ResidenteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/residentes")
@RequiredArgsConstructor
public class ResidenteController {

    private final ResidenteService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResidenteResponse crear(@Valid @RequestBody ResidenteRequest req) {
        return service.crear(req);
    }

    @GetMapping
    public List<ResidenteResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResidenteResponse obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PutMapping("/{id}")
    public ResidenteResponse actualizar(@PathVariable Long id, @Valid @RequestBody ResidenteRequest req) {
        return service.actualizar(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desactivar(@PathVariable Long id) {
        service.desactivar(id);
    }
}
