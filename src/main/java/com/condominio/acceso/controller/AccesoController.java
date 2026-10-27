package com.condominio.acceso.controller;

import com.condominio.acceso.dto.AccesoResponse;
import com.condominio.acceso.dto.RegistroAccesoResponse;
import com.condominio.acceso.dto.ValidarAccesoRequest;
import com.condominio.acceso.service.AccesoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/accesos")
@RequiredArgsConstructor
public class AccesoController {

    private final AccesoService service;

    /** Endpoint que usa la caseta/guardia para validar un código. */
    @PostMapping("/validar")
    public AccesoResponse validar(@Valid @RequestBody ValidarAccesoRequest req) {
        return service.validar(req);
    }

    @GetMapping
    public List<RegistroAccesoResponse> historial(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime hasta) {
        return service.historial(desde, hasta);
    }
}
