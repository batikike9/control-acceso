package com.condominio.acceso.dto;

import com.condominio.acceso.model.Residente;

public record ResidenteResponse(Long id, String nombre, String apellidos, String email,
                                String telefono, UnidadResponse unidad, boolean activo) {
    public static ResidenteResponse from(Residente r) {
        return new ResidenteResponse(r.getId(), r.getNombre(), r.getApellidos(), r.getEmail(),
                r.getTelefono(), UnidadResponse.from(r.getUnidad()), r.isActivo());
    }
}
